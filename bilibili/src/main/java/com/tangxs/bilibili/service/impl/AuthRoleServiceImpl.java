package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.AuthRole;
import com.tangxs.bilibili.service.AuthRoleService;
import com.tangxs.bilibili.mapper.AuthRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_auth_role(权限控制--角色表)】的数据库操作Service实现
* @createDate 2023-10-01 23:34:03
*/
@Service
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRole>
    implements AuthRoleService{

}




