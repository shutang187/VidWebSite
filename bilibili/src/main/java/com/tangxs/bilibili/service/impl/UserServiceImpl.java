package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.User;
import com.tangxs.bilibili.service.UserService;
import com.tangxs.bilibili.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_user(用户表)】的数据库操作Service实现
* @createDate 2023-10-01 23:34:04
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




