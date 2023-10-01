package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.FollowingGroup;
import com.tangxs.bilibili.service.FollowingGroupService;
import com.tangxs.bilibili.mapper.FollowingGroupMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_following_group(用户关注分组表)】的数据库操作Service实现
* @createDate 2023-10-01 23:34:04
*/
@Service
public class FollowingGroupServiceImpl extends ServiceImpl<FollowingGroupMapper, FollowingGroup>
    implements FollowingGroupService{

}




