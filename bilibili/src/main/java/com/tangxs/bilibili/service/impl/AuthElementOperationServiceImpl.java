package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.AuthElementOperation;
import com.tangxs.bilibili.service.AuthElementOperationService;
import com.tangxs.bilibili.mapper.AuthElementOperationMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_auth_element_operation(权限控制--页面元素操作表)】的数据库操作Service实现
* @createDate 2023-10-01 23:34:03
*/
@Service
public class AuthElementOperationServiceImpl extends ServiceImpl<AuthElementOperationMapper, AuthElementOperation>
    implements AuthElementOperationService{

}




