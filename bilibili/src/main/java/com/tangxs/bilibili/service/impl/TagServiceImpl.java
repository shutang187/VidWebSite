package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.Tag;
import com.tangxs.bilibili.service.TagService;
import com.tangxs.bilibili.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_tag(标签表)】的数据库操作Service实现
* @createDate 2023-10-14 22:04:15
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




