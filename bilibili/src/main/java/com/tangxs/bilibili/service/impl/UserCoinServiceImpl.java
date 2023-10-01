package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.UserCoin;
import com.tangxs.bilibili.service.UserCoinService;
import com.tangxs.bilibili.mapper.UserCoinMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_user_coin(用户硬币数量表)】的数据库操作Service实现
* @createDate 2023-10-01 23:34:04
*/
@Service
public class UserCoinServiceImpl extends ServiceImpl<UserCoinMapper, UserCoin>
    implements UserCoinService{

}




