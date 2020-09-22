package com.learning.patterns.listener.google.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * The type Executor services.
 *
 * @author wangzhen
 * @date 2020/9/22
 */
@Slf4j
public final class ThreadExecutors {
    /**
     * The constant ALIVE_TIME.
     */
    private static final Integer ALIVE_TIME = 5;

    /**
     * The constant QUEUE_CAPACITY.
     */
    private static final Integer QUEUE_CAPACITY = 1024;

    /**
     * The constant DEFAULT_POOL_SIZE.
     */
    private static final Integer DEFAULT_POOL_SIZE = 32;

    /**
     * The constant FACTOR.
     */
    private static final Integer FACTOR = 3;

    /**
     * Gets single thread executor.
     *
     * @param prefix the prefix
     * @return the single thread executor
     */
    public static Executor getSingleThreadExecutor(final String prefix) {
        return getThreadExecutor(prefix, false, 1, 1, null, null);
    }

    /**
     * Gets thread executor.
     *
     * @return the thread executor
     */
    public static Executor getThreadExecutor() {
        return getThreadExecutor("default", false, null, null, null, null);
    }

    /**
     * Gets thread executor.
     *
     * @param prefix the prefix
     * @return the thread executor
     */
    public static Executor getThreadExecutor(final String prefix) {
        return getThreadExecutor(prefix, false, null, null, null, null);
    }

    /**
     * Gets thread executor.
     *
     * @param prefix       the prefix
     * @param isDaemon     the is daemon
     * @param corePoolSize the threads
     * @param maxPoolSize  the max pool size
     * @return the task executor
     */
    public static Executor getThreadExecutor(final String prefix, final Boolean isDaemon, Integer corePoolSize, Integer maxPoolSize, Integer capacity, RejectedExecutionHandler rejectedExecutionHandler) {
        ThreadFactory threadFactory = new NamedThreadFactory(prefix, isDaemon);
        if (null == corePoolSize || corePoolSize < 0) {
            corePoolSize = DEFAULT_POOL_SIZE;
        }

        if (null == maxPoolSize || maxPoolSize < corePoolSize) {
            maxPoolSize = corePoolSize * FACTOR;
        }

        if (null == rejectedExecutionHandler) {
            rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
        }

        if (null == capacity) {
            capacity = QUEUE_CAPACITY;
        }

        return new ThreadPoolExecutor(corePoolSize, maxPoolSize, ALIVE_TIME, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(capacity), threadFactory, rejectedExecutionHandler) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                log.debug("threadExecutor prefix={},poolInfo={}", prefix, toString());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
            }
        };
    }
}