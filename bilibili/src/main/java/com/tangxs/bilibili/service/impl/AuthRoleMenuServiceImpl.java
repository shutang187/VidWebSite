package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.AuthRoleMenu;
import com.tangxs.bilibili.service.AuthRoleMenuService;
import com.tangxs.bilibili.mapper.AuthRoleMenuMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_auth_role_menu(权限控制--角色页面菜单关联表)】的数据库操作Service实现
* @createDate 2023-10-01 23:34:04
*/
@Service
public class AuthRoleMenuServiceImpl extends ServiceImpl<AuthRoleMenuMapper, AuthRoleMenu>
    implements AuthRoleMenuService{

}




