package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.VideoCoin;
import com.tangxs.bilibili.service.VideoCoinService;
import com.tangxs.bilibili.mapper.VideoCoinMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_video_coin(视频投币记录表)】的数据库操作Service实现
* @createDate 2023-10-14 22:04:15
*/
@Service
public class VideoCoinServiceImpl extends ServiceImpl<VideoCoinMapper, VideoCoin>
    implements VideoCoinService{

}




