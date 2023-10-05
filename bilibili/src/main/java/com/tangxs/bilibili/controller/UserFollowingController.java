package com.tangxs.bilibili.controller;

import com.tangxs.bilibili.constant.enums.ExceptionCode;
import com.tangxs.bilibili.domain.model.ResponseBean;
import com.tangxs.bilibili.domain.vo.FollowGroupVo;
import com.tangxs.bilibili.domain.vo.UserFollowBriefVo;
import com.tangxs.bilibili.domain.vo.UserFollowGroupVo;
import com.tangxs.bilibili.domain.vo.UserFollowVo;
import com.tangxs.bilibili.exception.GlobalException;
import com.tangxs.bilibili.service.UserFollowingService;
import com.tangxs.bilibili.util.ResponseUtil;
import com.tangxs.bilibili.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author tangxs
 * @Description //TODO
 * @Date 2023/10/4 21:41
 **/
@RestController
@RequestMapping("/follow")
public class UserFollowingController {

    @Autowired
    private UserFollowingService userFollowingService;

    @Autowired
    private TokenUtil tokenUtil;

    @GetMapping("/getFollowing/v1")
    public ResponseBean<List<UserFollowVo>> getCurrentUserFollowing(){
        Long loginUserId;
        try {
            loginUserId = tokenUtil.getLoginUserId();
        } catch (Exception e) {
            throw new GlobalException(ExceptionCode.TOKEN_VERIFY_ERROR);
        }
        List<UserFollowVo> userFollowVos = userFollowingService.getCurrentUserFollowing(loginUserId);
        return ResponseUtil.success(userFollowVos);
    }

    @GetMapping("/getFans/v1")
    public ResponseBean<List<UserFollowVo>> getCurrentUserFans(){
        Long loginUserId;
        try {
            loginUserId = tokenUtil.getLoginUserId();
        } catch (Exception e) {
            throw new GlobalException(ExceptionCode.TOKEN_VERIFY_ERROR);
        }
        List<UserFollowVo> userFollowVos = userFollowingService.getCurrentUserFans(loginUserId);
        return ResponseUtil.success(userFollowVos);
    }

    @GetMapping("/getFollowing/v2")
    public ResponseBean<List<UserFollowGroupVo>> getUserFollowingDetail(){
        Long loginUserId;
        try {
            loginUserId = tokenUtil.getLoginUserId();
        } catch (Exception e) {
            throw new GlobalException(ExceptionCode.TOKEN_VERIFY_ERROR);
        }
        List<UserFollowGroupVo> userFollowGroupVos = userFollowingService.getUserFollowingDetail(loginUserId);
        return ResponseUtil.success(userFollowGroupVos);
    }

    @GetMapping("/getFans/v2")
    public ResponseBean<List<UserFollowVo>> getUserFansDetail(){
        Long loginUserId;
        try {
            loginUserId = tokenUtil.getLoginUserId();
        } catch (Exception e) {
            throw new GlobalException(ExceptionCode.TOKEN_VERIFY_ERROR);
        }

        List<UserFollowVo> userFollowVos = userFollowingService.getUserFansDetail(loginUserId);
        return ResponseUtil.success(userFollowVos);
    }


    @PostMapping("/addFollow")
    public ResponseBean addUserFollowing(@RequestBody UserFollowBriefVo userFollowBriefVo){

        userFollowingService.addUserFollowing(userFollowBriefVo);

        return ResponseUtil.success();
    }

    @PostMapping("/delete/follow")
    public ResponseBean deleteUserFollowing(@RequestBody UserFollowBriefVo userFollowBriefVo){
        userFollowingService.deleteUserFollowing(userFollowBriefVo);
        return ResponseUtil.success();
    }

    @GetMapping("/group")
    public ResponseBean<List<FollowGroupVo>> getFollowingGroup(){
        Long loginUserId;
        try {
            loginUserId = tokenUtil.getLoginUserId();
        } catch (Exception e) {
            throw new GlobalException(ExceptionCode.TOKEN_VERIFY_ERROR);
        }

        List<FollowGroupVo> followGroupVos=userFollowingService.getFollowingGroup(loginUserId);
        return ResponseUtil.success(followGroupVos);
    }

    @PostMapping("/update/group")
    public ResponseBean updateFollowingGroup(FollowGroupVo followGroupVo){
        userFollowingService.updateFollowingGroup(followGroupVo);
        return ResponseUtil.success();
    }

    @PostMapping("/delete/group")
    public ResponseBean deleteFolowingGroup(FollowGroupVo followGroupVo){
        userFollowingService.deleteFollowingGroup(followGroupVo);
        return ResponseUtil.success();
    }

}
