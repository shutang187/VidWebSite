package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.VideoOperation;
import com.tangxs.bilibili.service.VideoOperationService;
import com.tangxs.bilibili.mapper.VideoOperationMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_video_operation(用户操作表)】的数据库操作Service实现
* @createDate 2023-10-14 22:04:15
*/
@Service
public class VideoOperationServiceImpl extends ServiceImpl<VideoOperationMapper, VideoOperation>
    implements VideoOperationService{

}




