package com.tangxs.bilibili.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @Author tangxs
 * @Description 应用退出时关闭后台线程
 * @Date 2023/10/15 16:13
 **/

@Component
@Slf4j
public class ShutdownManager {

    @Autowired
    private AsyncManager asyncManager;

    @PreDestroy
    public void destroy(){
        try {
            log.info("#########关闭后台任务线程#######");
            asyncManager.shutdown();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }
}
