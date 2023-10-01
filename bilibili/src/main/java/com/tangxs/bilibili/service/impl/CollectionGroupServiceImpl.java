package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.CollectionGroup;
import com.tangxs.bilibili.service.CollectionGroupService;
import com.tangxs.bilibili.mapper.CollectionGroupMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_collection_group(用户收藏分组表)】的数据库操作Service实现
* @createDate 2023-10-01 23:34:04
*/
@Service
public class CollectionGroupServiceImpl extends ServiceImpl<CollectionGroupMapper, CollectionGroup>
    implements CollectionGroupService{

}




