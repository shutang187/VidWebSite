package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.UserFollowing;
import com.tangxs.bilibili.service.UserFollowingService;
import com.tangxs.bilibili.mapper.UserFollowingMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_user_following(用户关注表)】的数据库操作Service实现
* @createDate 2023-10-01 23:34:04
*/
@Service
public class UserFollowingServiceImpl extends ServiceImpl<UserFollowingMapper, UserFollowing>
    implements UserFollowingService{

}




