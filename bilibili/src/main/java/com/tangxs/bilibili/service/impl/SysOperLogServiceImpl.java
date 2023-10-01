package com.tangxs.bilibili.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangxs.bilibili.domain.dao.SysOperLog;
import com.tangxs.bilibili.service.SysOperLogService;
import com.tangxs.bilibili.mapper.SysOperLogMapper;
import org.springframework.stereotype.Service;

/**
* @author tangxs
* @description 针对表【t_sys_oper_log(操作日志记录)】的数据库操作Service实现
* @createDate 2023-10-01 23:34:04
*/
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog>
    implements SysOperLogService{

}




