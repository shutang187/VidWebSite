package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.File;
import com.tangxs.bilibili.service.FileService;
import com.tangxs.bilibili.mapper.FileMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_file(上传文件信息表)】的数据库操作Service实现
* @createDate 2023-10-14 22:04:15
*/
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File>
    implements FileService{

}




