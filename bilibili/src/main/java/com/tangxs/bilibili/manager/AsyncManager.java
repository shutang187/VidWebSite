package com.tangxs.bilibili.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author tangxs
 * @Description 异步任务管理器
 * @Date 2023/10/15 9:44
 **/
@Slf4j
@Component
public class AsyncManager {

    @Autowired
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;



    public void execute(TimerTask task, int delayTime, TimeUnit unit){
        scheduledThreadPoolExecutor.schedule(task,delayTime,unit);
    }


    /**
     * 停止线程池
     * 先使用shutdown, 停止接收新任务并尝试完成所有已存在任务.
     * 如果超时, 则调用shutdownNow, 取消在workQueue中Pending的任务,并中断所有阻塞函数.
     * 如果仍人超時，則強制退出.
     * 另对在shutdown时线程本身被调用中断做了处理.
     */
    public void shutdown(){
        if (scheduledThreadPoolExecutor != null && !scheduledThreadPoolExecutor.isShutdown()) {
            scheduledThreadPoolExecutor.shutdown();
            try {
                if (!scheduledThreadPoolExecutor.awaitTermination(120, TimeUnit.SECONDS)) {
                    scheduledThreadPoolExecutor.shutdownNow();
                    if (!scheduledThreadPoolExecutor.awaitTermination(120, TimeUnit.SECONDS)) {
                        log.info("Pool did not terminate");
                    }
                }
            } catch (InterruptedException ie) {
                scheduledThreadPoolExecutor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

}
