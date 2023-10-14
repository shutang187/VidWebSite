package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.VideoLike;
import com.tangxs.bilibili.service.VideoLikeService;
import com.tangxs.bilibili.mapper.VideoLikeMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_video_like(视频点赞表)】的数据库操作Service实现
* @createDate 2023-10-14 22:04:15
*/
@Service
public class VideoLikeServiceImpl extends ServiceImpl<VideoLikeMapper, VideoLike>
    implements VideoLikeService{

}




