package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.VideoTag;
import com.tangxs.bilibili.service.VideoTagService;
import com.tangxs.bilibili.mapper.VideoTagMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_video_tag(视频标签关联表)】的数据库操作Service实现
* @createDate 2023-10-14 22:04:15
*/
@Service
public class VideoTagServiceImpl extends ServiceImpl<VideoTagMapper, VideoTag>
    implements VideoTagService{

}




