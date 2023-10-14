package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.VideoComment;
import com.tangxs.bilibili.service.VideoCommentService;
import com.tangxs.bilibili.mapper.VideoCommentMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_video_comment(视频评论表)】的数据库操作Service实现
* @createDate 2023-10-14 22:04:15
*/
@Service
public class VideoCommentServiceImpl extends ServiceImpl<VideoCommentMapper, VideoComment>
    implements VideoCommentService{

}




