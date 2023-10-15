package com.tangxs.bilibili.config;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author tangxs
 * @Description 线程池配置
 * @Date 2023/10/15 9:47
 **/
@Configuration
public class ThreadPoolConfig {

    private int corePoolSize = 40;

    private int maxPoolSize = 100;

    private int queueCapacity = 100;

    private int keepAliveSeconds = 180;

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolTaskExecutor;
    }

    @Bean(name = "scheduledThreadPoolExecutor")
    public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor(){
        return new ScheduledThreadPoolExecutor(corePoolSize,new ThreadFactoryBuilder().setNamePrefix("schedule-pool-").setDaemon(true).build());
    }


}
