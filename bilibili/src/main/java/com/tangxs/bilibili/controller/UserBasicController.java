package com.tangxs.bilibili.controller;

import com.tangxs.bilibili.annotation.Log;
import com.tangxs.bilibili.constant.enums.BusinessType;
import com.tangxs.bilibili.domain.model.ResponseBean;
import com.tangxs.bilibili.domain.vo.LoginUserVo;
import com.tangxs.bilibili.domain.vo.UserInfoVo;
import com.tangxs.bilibili.service.UserService;
import com.tangxs.bilibili.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author tangxs
 * @Description
 * @Date 2023/10/3 17:43
 **/
@RestController
@RequestMapping("/user")
public class UserBasicController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseBean register(@RequestBody LoginUserVo loginUserVo){
        userService.register(loginUserVo);
        return ResponseUtil.success();
    }


    @PostMapping("/login")
    public ResponseBean<String> login(@RequestBody LoginUserVo loginUserVo) throws Exception {
        String token = userService.login(loginUserVo);
        return ResponseUtil.success(token);
    }

    @GetMapping("/getUserInfo")
    @Log(businessType = BusinessType.QUERY,title = "获取当前用户信息",isSaveRequestData = true)
    public ResponseBean<UserInfoVo> getUserInfo(){
        UserInfoVo userInfoVo = userService.getCurrentUserInfo();
        return ResponseUtil.success(userInfoVo);
    }

    @PostMapping("/updateUserInfo")
    public ResponseBean updateUserInfo(@RequestBody UserInfoVo userInfoVo){
        userService.updateUserInfo(userInfoVo);
        return ResponseUtil.success();
    }

    @GetMapping("/logout")
    private ResponseBean logout(){
        userService.logout();
        return ResponseUtil.success();
    }


}
