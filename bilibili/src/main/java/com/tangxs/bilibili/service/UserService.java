package com.tangxs.bilibili.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangxs.bilibili.domain.dao.User;
import com.tangxs.bilibili.domain.model.ResponseBean;
import com.tangxs.bilibili.domain.vo.LoginUserVo;
import com.tangxs.bilibili.domain.vo.UserInfoVo;

import javax.servlet.http.HttpServletRequest;

/**
* @author tangxs
* @description 针对表【t_user(用户表)】的数据库操作Service
* @createDate 2023-10-01 23:34:04
*/
public interface UserService extends IService<User> {


    void register(LoginUserVo loginUserVo);

    String login(LoginUserVo loginUserVo) throws Exception;

    UserInfoVo getCurrentUserInfo(HttpServletRequest httpServletRequest);

    void updateUserInfo(UserInfoVo userInfoVo,HttpServletRequest httpServletRequest);

    void logout(HttpServletRequest httpServletRequest);
}
