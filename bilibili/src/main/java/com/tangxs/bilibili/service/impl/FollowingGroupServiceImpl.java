package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.FollowingGroup;
import com.tangxs.bilibili.service.FollowingGroupService;
import com.tangxs.bilibili.mapper.FollowingGroupMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author tangxs
* @description 针对表【t_following_group(用户关注分组表)】的数据库操作Service实现
* @createDate 2023-10-01 23:34:04
*/
@Service
public class FollowingGroupServiceImpl extends ServiceImpl<FollowingGroupMapper, FollowingGroup>
    implements FollowingGroupService{

    @Override
    public List<FollowingGroup> getGroupByUserId(Long userId) {
        QueryWrapper<FollowingGroup> followingGroupQueryWrapper = new QueryWrapper<>();
        followingGroupQueryWrapper.eq("user_id",userId);
        List<FollowingGroup> followingGroups = this.list(followingGroupQueryWrapper);
        return followingGroups;
    }

    @Override
    public FollowingGroup getGroupByType(String defaultGroupType) {
        QueryWrapper<FollowingGroup> followingGroupQueryWrapper = new QueryWrapper<>();
        followingGroupQueryWrapper.eq("type",defaultGroupType);
        FollowingGroup followingGroup = this.getOne(followingGroupQueryWrapper);
        return followingGroup;
    }
}




