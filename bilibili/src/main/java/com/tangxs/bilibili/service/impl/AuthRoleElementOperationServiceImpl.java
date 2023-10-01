package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.AuthRoleElementOperation;
import com.tangxs.bilibili.service.AuthRoleElementOperationService;
import com.tangxs.bilibili.mapper.AuthRoleElementOperationMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_auth_role_element_operation(权限控制--角色与元素操作关联表)】的数据库操作Service实现
* @createDate 2023-10-01 23:34:04
*/
@Service
public class AuthRoleElementOperationServiceImpl extends ServiceImpl<AuthRoleElementOperationMapper, AuthRoleElementOperation>
    implements AuthRoleElementOperationService{

}




