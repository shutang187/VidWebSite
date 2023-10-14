package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.VideoCollection;
import com.tangxs.bilibili.service.VideoCollectionService;
import com.tangxs.bilibili.mapper.VideoCollectionMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_video_collection(视频收藏表)】的数据库操作Service实现
* @createDate 2023-10-14 22:04:15
*/
@Service
public class VideoCollectionServiceImpl extends ServiceImpl<VideoCollectionMapper, VideoCollection>
    implements VideoCollectionService{

}




