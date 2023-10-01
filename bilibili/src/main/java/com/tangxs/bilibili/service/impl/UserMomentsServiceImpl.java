package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.UserMoments;
import com.tangxs.bilibili.service.UserMomentsService;
import com.tangxs.bilibili.mapper.UserMomentsMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_user_moments(用户动态表)】的数据库操作Service实现
* @createDate 2023-10-01 23:34:04
*/
@Service
public class UserMomentsServiceImpl extends ServiceImpl<UserMomentsMapper, UserMoments>
    implements UserMomentsService{

}




