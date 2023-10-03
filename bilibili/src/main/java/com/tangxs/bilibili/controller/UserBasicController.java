package com.tangxs.bilibili.controller;

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
    public ResponseBean<UserInfoVo> getUserInfo(HttpServletRequest httpServletRequest){
        UserInfoVo userInfoVo = userService.getCurrentUserInfo(httpServletRequest);
        return ResponseUtil.success(userInfoVo);
    }

    @PostMapping("/updateUserInfo")
    public ResponseBean updateUserInfo(@RequestBody UserInfoVo userInfoVo,HttpServletRequest httpServletRequest){
        userService.updateUserInfo(userInfoVo,httpServletRequest);
        return ResponseUtil.success();
    }

    @GetMapping("/logout")
    private ResponseBean logout(HttpServletRequest httpServletRequest){
        userService.logout(httpServletRequest);
        return ResponseUtil.success();
    }


}
