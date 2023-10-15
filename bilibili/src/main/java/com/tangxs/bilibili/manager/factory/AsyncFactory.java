package com.tangxs.bilibili.manager.factory;

import cn.hutool.json.JSONUtil;
import com.tangxs.bilibili.domain.dao.SysOperLog;
import com.tangxs.bilibili.service.SysOperLogService;
import com.tangxs.bilibili.util.AddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

/**
 * @Author tangxs
 * @Description 异步任务工厂
 * @Date 2023/10/15 16:38
 **/
@Slf4j(topic = "sys-user")
@Component
public class AsyncFactory {

    private static SysOperLogService sysOperLogService;

    @Autowired
    public void setSysOperLogService(SysOperLogService sysOperLogService) {
        AsyncFactory.sysOperLogService = sysOperLogService;
    }

    /**
     * @Description 操作日志记录
     * @Date 2023/10/15 17:02
     **/
    public static TimerTask recordOper(final SysOperLog sysOperLog){
        return new TimerTask() {
            @Override
            public void run() {
                sysOperLog.setOperLocation(AddressUtil.getRealAddressByIP(sysOperLog.getOperIp()));
                sysOperLogService.save(sysOperLog);
                log.info("插入操作日志：", JSONUtil.toJsonStr(sysOperLog));
            }
        };
    }


}
