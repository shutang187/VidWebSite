package com.tangxs.bilibili.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangxs.bilibili.domain.dao.UserFollowing;
import com.tangxs.bilibili.domain.vo.FollowGroupVo;
import com.tangxs.bilibili.domain.vo.UserFollowBriefVo;
import com.tangxs.bilibili.domain.vo.UserFollowGroupVo;
import com.tangxs.bilibili.domain.vo.UserFollowVo;

import java.util.List;

/**
* @author tangxs
* @description 针对表【t_user_following(用户关注表)】的数据库操作Service
* @createDate 2023-10-01 23:34:04
*/
public interface UserFollowingService extends IService<UserFollowing> {

    /**
     * @Author tangxs
     * @Description 获取当前用户的所有关注用户的信息
     * @Date 2023/10/4 21:54
     **/
    List<UserFollowVo> getCurrentUserFollowing(Long userId);

    /**
     * @Author tangxs
     * @Description 获取当前用户的所有粉丝信息
     * @Date 2023/10/4 21:54
     **/
    List<UserFollowVo> getCurrentUserFans(Long userId);

    /**
     * @Author tangxs
     * @Description 获取当前用户的关注用户信息，会进行相应的分组
     * @Date 2023/10/4 22:23
     **/
    List<UserFollowGroupVo> getUserFollowingDetail(Long userId);

    /**
     * @Author tangxs
     * @Description 查询用户的粉丝信息，包含粉丝的详细信息
     * @Date 2023/10/4 22:24
     **/
    List<UserFollowVo> getUserFansDetail(Long userId);

    void addUserFollowing(UserFollowBriefVo userFollowBriefVo);

    void deleteUserFollowing(UserFollowBriefVo userFollowBriefVo);

    List<FollowGroupVo> getFollowingGroup(Long loginUserId);

    void updateFollowingGroup(FollowGroupVo followGroupVo);

    void deleteFollowingGroup(FollowGroupVo followGroupVo);
}
