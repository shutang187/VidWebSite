package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.Video;
import com.tangxs.bilibili.service.VideoService;
import com.tangxs.bilibili.mapper.VideoMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_video(视频投稿记录表)】的数据库操作Service实现
* @createDate 2023-10-14 22:04:15
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService{

}




