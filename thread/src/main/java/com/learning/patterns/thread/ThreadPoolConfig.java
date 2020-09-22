package com.learning.patterns.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yanghuadong
 * @date 2020-05-27
 */
@Configuration
public class ThreadPoolConfig {

    @Bean(name = "defaultExecutor")
    public Executor defaultExecutor() {
        return ThreadExecutors.getThreadExecutor();
    }

    @Bean(name = "aggregationExecutor")
    public Executor aggregationExecutor() {
        return ThreadExecutors.getThreadExecutor(
                "aggr", true,
                8, 32,
                1024, new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    @Bean(name = "buildWebKlineExecutor")
    public Executor buildWebKlineExecutor() {
        return ThreadExecutors.getThreadExecutor(
                "buildWebKline", true,
                8, 32,
                2048, new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Bean(name = "buildSymbolAggrExecutor")
    public Executor buildSymbolAggrExecutor() {
        return ThreadExecutors.getThreadExecutor(
                "buildWebSymbolAggr", true,
                8, 32,
                4096, new ThreadPoolExecutor.DiscardOldestPolicy());
    }
}