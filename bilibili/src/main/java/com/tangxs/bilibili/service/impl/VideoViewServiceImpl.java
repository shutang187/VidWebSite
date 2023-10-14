package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.VideoView;
import com.tangxs.bilibili.service.VideoViewService;
import com.tangxs.bilibili.mapper.VideoViewMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_video_view(视频观看记录表)】的数据库操作Service实现
* @createDate 2023-10-14 22:04:15
*/
@Service
public class VideoViewServiceImpl extends ServiceImpl<VideoViewMapper, VideoView>
    implements VideoViewService{

}




