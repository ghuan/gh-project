package com.gh.common.core.config;

import com.gh.common.core.properties.AsyncProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * 异步调用执行器线程池配置
 * springboot 主函数main方法上添加@EnableAsync 以启用@Async注解
 * 方法上注解@Async 标志异步调用
 */
@Configuration
public class AsyncConfig {

    @Resource
    private AsyncProperties asyncProperties;

    /**
     * spring schedule 异步任务 默认执行器配置
     * @return
     */
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(asyncProperties.getCorePoolSize());
        executor.setMaxPoolSize(asyncProperties.getMaxPoolSize());
        executor.setQueueCapacity(asyncProperties.getQueueCapacity());
        executor.setThreadNamePrefix("taskExecutor");
        executor.initialize();
        return executor;
    }

    /**
     * 自定义调度器配置
     * @return
     */
//    @Bean
//    public Executor myTaskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(corePoolSize);
//        executor.setMaxPoolSize(maxPoolSize);
//        executor.setQueueCapacity(queueCapacity);
//        executor.setThreadNamePrefix("MyTaskExecutor");
//        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
//        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        executor.initialize();
//        return executor;
//    }
}
