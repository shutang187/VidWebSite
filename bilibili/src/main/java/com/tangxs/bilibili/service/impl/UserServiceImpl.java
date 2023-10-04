package com.tangxs.bilibili.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.constant.RoleCode;
import com.tangxs.bilibili.domain.dao.AuthRole;
import com.tangxs.bilibili.domain.dao.User;
import com.tangxs.bilibili.domain.dao.UserCoin;
import com.tangxs.bilibili.domain.dao.UserRole;
import com.tangxs.bilibili.domain.model.LoginUser;
import com.tangxs.bilibili.domain.model.ResponseBean;
import com.tangxs.bilibili.domain.vo.LoginUserVo;
import com.tangxs.bilibili.domain.vo.UserInfoVo;
import com.tangxs.bilibili.exception.GlobalException;
import com.tangxs.bilibili.service.AuthRoleService;
import com.tangxs.bilibili.service.UserCoinService;
import com.tangxs.bilibili.service.UserRoleService;
import com.tangxs.bilibili.service.UserService;
import com.tangxs.bilibili.mapper.UserMapper;
import com.tangxs.bilibili.util.*;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
* @author tangxs
* @description 针对表【t_user(用户表)】的数据库操作Service实现
* @createDate 2023-10-01 23:34:04
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private AuthRoleService authRoleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserCoinService userCoinService;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    @Transactional
    public void register(LoginUserVo loginUserVo) {
        String phone = loginUserVo.getPhone();
        if (StrUtil.isEmpty(phone)) {
            throw new GlobalException("手机号不能为空");
        }

        User user = getUserByPhone(phone);
        if (user != null) {
            throw new GlobalException("该手机号已经注册");
        }

        Date date = new Date();
        String salt = String.valueOf(date);
        String rsaPassword;
        try {
            rsaPassword = RSAUtil.decrypt(loginUserVo.getPassword());
        } catch (Exception e) {
            throw new GlobalException("密码解析异常");
        }
        String md5Password = MD5Util.sign(rsaPassword,salt , CharsetUtil.UTF_8);
        User registerUser = BeanUtil.copyProperties(loginUserVo, User.class);
        registerUser.setSalt(salt);
        registerUser.setPassword(md5Password);
        this.save(registerUser);
        addUserDefaultRole(registerUser.getId());
        addUserDefaultCoin(registerUser.getId());
    }

    @Override
    public String login(LoginUserVo loginUserVo) throws Exception {
        String phone = loginUserVo.getPhone();
        if (StrUtil.isEmpty(phone)) {
            throw new GlobalException("手机号不能为空");
        }

        User user = getUserByPhone(phone);
        if (user == null) {
            throw new GlobalException("当前用户不存在");
        }

        String rsaPassword;
        try {
            rsaPassword = RSAUtil.decrypt(loginUserVo.getPassword());
        } catch (Exception e) {
            throw new GlobalException("密码解析异常");
        }
        String md5Password = MD5Util.sign(rsaPassword,user.getSalt(),CharsetUtil.UTF_8);
        if (!user.getPassword().equals(md5Password)){
            throw new GlobalException("密码错误");
        }
        String token = tokenUtil.generateToken(user.getId());
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getId());
        loginUser.setUser(user);
        tokenUtil.setLoginUser(loginUser);
        return token;
    }

    @Override
    public UserInfoVo getCurrentUserInfo(HttpServletRequest httpServletRequest){
        Long loginUserId;
        try {
            loginUserId = tokenUtil.getLoginUserId(httpServletRequest);
        } catch (Exception e) {
            throw new GlobalException("token 非法");
        }
        LoginUser loginUser = tokenUtil.getLoginUser(loginUserId);
        if (loginUser == null){
             throw new GlobalException("token失效，请重新登录");
        }
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanUtil.copyProperties(user, UserInfoVo.class);
        return userInfoVo;
    }

    @Override
    public void updateUserInfo(UserInfoVo userInfoVo,HttpServletRequest httpServletRequest) {
        Long loginUserId;
        try {
            loginUserId = tokenUtil.getLoginUserId(httpServletRequest);
        } catch (Exception e) {
            throw new GlobalException("token非法");
        }
        User user = BeanUtil.copyProperties(userInfoVo, User.class);
        user.setId(loginUserId);
        this.updateById(user);
        redisCacheUtil.deleteObject(TokenUtil.getTokenKey(loginUserId));
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest) {
        try {
            tokenUtil.deleteLoginUser(httpServletRequest);
        } catch (Exception e) {
            throw new GlobalException("token 非法");
        }
    }

    private void addUserDefaultCoin(Long id) {
        UserCoin userCoin = new UserCoin();
        userCoin.setUserId(id);
        userCoin.setAmount(0L);
        userCoinService.save(userCoin);
    }

    private void addUserDefaultRole(Long id) {
        UserRole userRole = new UserRole();
        AuthRole authRole = authRoleService.getRoleByCode(RoleCode.ROLE_LV0);
        userRole.setUserId(id);
        userRole.setRoleId(authRole.getId());
        userRoleService.save(userRole);
    }

    private User getUserByPhone(String phone) {
        return this.getOne(new QueryWrapper<User>().eq("phone", phone));
    }



}




