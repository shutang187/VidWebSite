package com.tangxs.bilibili.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.constant.UserConstant;
import com.tangxs.bilibili.domain.dao.FollowingGroup;
import com.tangxs.bilibili.domain.dao.User;
import com.tangxs.bilibili.domain.dao.UserFollowing;
import com.tangxs.bilibili.domain.vo.*;
import com.tangxs.bilibili.exception.GlobalException;
import com.tangxs.bilibili.service.FollowingGroupService;
import com.tangxs.bilibili.service.UserFollowingService;
import com.tangxs.bilibili.mapper.UserFollowingMapper;
import com.tangxs.bilibili.service.UserService;
import com.tangxs.bilibili.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author tangxs
* @description 针对表【t_user_following(用户关注表)】的数据库操作Service实现
* @createDate 2023-10-01 23:34:04
*/
@Service
public class UserFollowingServiceImpl extends ServiceImpl<UserFollowingMapper, UserFollowing>
    implements UserFollowingService{

    @Autowired
    private UserService userService;

    @Autowired
    private FollowingGroupService followingGroupService;

    @Autowired
    private TokenUtil tokenUtil;


    @Override
    public List<UserFollowVo> getCurrentUserFollowing(Long userId) {
        QueryWrapper<UserFollowing> userFollowingQueryWrapper = new QueryWrapper<>();
        userFollowingQueryWrapper.eq("user_id",userId);
        List<UserFollowing> list = this.list(userFollowingQueryWrapper);
        List<UserFollowVo> userFollowVos = BeanUtil.copyToList(list, UserFollowVo.class);
        return userFollowVos;
    }

    @Override
    public List<UserFollowVo> getCurrentUserFans(Long userId) {
        QueryWrapper<UserFollowing> userFollowingQueryWrapper = new QueryWrapper<>();
        userFollowingQueryWrapper.eq("following_id",userId);
        List<UserFollowing> list = this.list(userFollowingQueryWrapper);
        List<UserFollowVo> userFollowVos = BeanUtil.copyToList(list, UserFollowVo.class);
        return userFollowVos;
    }

    @Override
    public List<UserFollowGroupVo> getUserFollowingDetail(Long userId) {
        List<UserFollowVo> currentUserFollowing = this.getCurrentUserFollowing(userId);

        List<FollowingGroup> followingGroupList = followingGroupService.getGroupByUserId(userId);
        List<UserFollowGroupVo> userFollowGroupVos = BeanUtil.copyToList(followingGroupList, UserFollowGroupVo.class);

        if (CollUtil.isEmpty(currentUserFollowing) || CollUtil.isEmpty(userFollowGroupVos)) {
            return null;
        }

        Set<Long> followIdSet = currentUserFollowing.stream().map(UserFollowVo::getFollowingId).collect(Collectors.toSet());
        List<User> userList = userService.listByIds(followIdSet);

        for(UserFollowVo userFollowVo:currentUserFollowing){
            for(User user: userList){
                if(userFollowVo.getFollowingId().equals(user.getId())){
                    UserInfoVo userInfoVo = BeanUtil.copyProperties(user, UserInfoVo.class);
                    userFollowVo.setUserInfoVo(userInfoVo);
                }
            }
        }


        for (UserFollowGroupVo userFollowGroupVo : userFollowGroupVos){
            ArrayList<UserInfoVo> userInfoVos = CollUtil.newArrayList();
            for(UserFollowVo userFollowVo : currentUserFollowing){
                if(userFollowGroupVo.getId().equals(userFollowVo.getGroupId())){
                    userInfoVos.add(userFollowVo.getUserInfoVo());
                }
            }
            userFollowGroupVo.setFollowingUserInfoList(userInfoVos);
        }
        return userFollowGroupVos;
    }

    @Override
    public List<UserFollowVo> getUserFansDetail(Long userId) {
        List<UserFollowVo> currentUserFans = this.getCurrentUserFans(userId);
        if(CollUtil.isEmpty(currentUserFans)){
            return null;
        }

        Set<Long> fanIdSet = currentUserFans.stream().map(UserFollowVo::getUserId).collect(Collectors.toSet());
        List<User> userList = userService.listByIds(fanIdSet);

        for(UserFollowVo userFollowVo:currentUserFans){
            for(User user:userList){
                if(userFollowVo.getUserId().equals(user.getId())){
                    UserInfoVo userInfoVo = BeanUtil.copyProperties(user, UserInfoVo.class);
                    userFollowVo.setUserInfoVo(userInfoVo);
                }
            }
        }

        return currentUserFans;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUserFollowing(UserFollowBriefVo userFollowBriefVo) {
        UserFollowing userFollowing = BeanUtil.copyProperties(userFollowBriefVo, UserFollowing.class);
        this.deleteUserFollowing(userFollowBriefVo);
        this.save(userFollowing);
    }

    private void validate(UserFollowBriefVo userFollowBriefVo) {
        Long userId = userFollowBriefVo.getUserId();
        Long followingId = userFollowBriefVo.getFollowingId();
        Long groupId = userFollowBriefVo.getGroupId();

        if(groupId == null){
            FollowingGroup followingGroup = followingGroupService.getGroupByType(UserConstant.DEFAULT_GROUP_TYPE);
            userFollowBriefVo.setGroupId(followingGroup.getId());
        }else{
            FollowingGroup followingGroup = followingGroupService.getById(groupId);
            if(followingGroup == null){
                throw new GlobalException("关注分组不存在");
            }
        }

        if (userId == null || followingId == null){
           throw new GlobalException("关注信息不全");
        }

        User followUser = userService.getById(followingId);
        if(followUser == null){
            throw new GlobalException("关注用户不存在");
        }

    }

    @Override
    public void deleteUserFollowing(UserFollowBriefVo userFollowBriefVo) {
        this.validate(userFollowBriefVo);
        QueryWrapper<UserFollowing> userFollowingQueryWrapper = new QueryWrapper<>();
        userFollowingQueryWrapper.eq("user_id",userFollowBriefVo.getUserId())
                .eq("following_id",userFollowBriefVo.getFollowingId());

        this.remove(userFollowingQueryWrapper);
    }

    @Override
    public List<FollowGroupVo> getFollowingGroup(Long loginUserId) {
        if(loginUserId == null){
            return null;
        }

        List<FollowingGroup> group = followingGroupService.getGroupByUserId(loginUserId);
        List<FollowGroupVo> followGroupVos = BeanUtil.copyToList(group, FollowGroupVo.class);
        return followGroupVos;
    }

    @Override
    public void updateFollowingGroup(FollowGroupVo followGroupVo) {
        if(followGroupVo == null){
            return;
        }

        FollowingGroup followingGroup = BeanUtil.copyProperties(followGroupVo, FollowingGroup.class);
        followingGroup.setType(UserConstant.USER_DEFINE_GROUP_TYPE);
        followingGroupService.save(followingGroup);
    }

    @Override
    public void deleteFollowingGroup(FollowGroupVo followGroupVo) {
        if(followGroupVo.getId() == null) {
            return;
        }
        followingGroupService.removeById(followGroupVo.getId());
    }
}




