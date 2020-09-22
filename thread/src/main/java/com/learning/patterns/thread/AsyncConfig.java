package com.learning.patterns.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.Executor;

/**
 * Configure Async to use a custom thread pool.
 *
 * @author yanghuadong
 * @date 2018-12-28
 */
@Configuration
@Slf4j
public class AsyncConfig implements AsyncConfigurer {
    /**
     * Gets async executor.
     *
     * @return the async executor
     */
    @Override
    public Executor getAsyncExecutor() {
        return ThreadExecutors.getThreadExecutor("async");
    }

    /**
     * Gets async uncaught exception handler.
     *
     * @return the async uncaught exception handler
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> log.error("Async uncaught " + throwable.getMessage(), throwable);
    }
}