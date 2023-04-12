package org.boot.configuration;

import org.boot.configuration.manger.ConfigManger;
import org.boot.context.ContextTaskDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * TaskConfig
 *
 * @author IIIDelay
 * @createTime 2023年04月12日 15:21:00
 */
public class TaskConfig {
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(ConfigManger cm) {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        CoustomConfig config = cm.getCoustomConfig();
        poolTaskExecutor.setCorePoolSize(config.getCorePoolSize());
        poolTaskExecutor.setMaxPoolSize(config.getMaxPoolSize());
        // 设置线程活跃时间（秒）
        poolTaskExecutor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        // 设置队列容量
        poolTaskExecutor.setQueueCapacity(config.getQueueCapacity());
        //设置TaskDecorator，用于解决父子线程间的数据复用, 配合spring TaskDecorator使用
        poolTaskExecutor.setTaskDecorator(new ContextTaskDecorator());
        poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        poolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return poolTaskExecutor;
    }
}
