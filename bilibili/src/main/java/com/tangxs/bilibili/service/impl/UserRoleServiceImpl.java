package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.UserRole;
import com.tangxs.bilibili.service.UserRoleService;
import com.tangxs.bilibili.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_user_role(用户角色关联表)】的数据库操作Service实现
* @createDate 2023-10-03 18:41:25
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




