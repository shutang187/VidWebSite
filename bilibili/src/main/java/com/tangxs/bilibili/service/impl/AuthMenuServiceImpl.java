package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.AuthMenu;
import com.tangxs.bilibili.service.AuthMenuService;
import com.tangxs.bilibili.mapper.AuthMenuMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_auth_menu(权限控制--页面访问表)】的数据库操作Service实现
* @createDate 2023-10-01 23:34:03
*/
@Service
public class AuthMenuServiceImpl extends ServiceImpl<AuthMenuMapper, AuthMenu>
    implements AuthMenuService{

}




