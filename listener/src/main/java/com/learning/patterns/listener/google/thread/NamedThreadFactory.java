package com.learning.patterns.listener.google.thread;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Custom thread factories.
 *
 * @author wangzhen
 * @date 2020/9/22
 */
@Slf4j
public class NamedThreadFactory implements ThreadFactory {
    /**
     * Thread pool number.
     */
    private static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

    /**
     * The thread number.
     */
    private final AtomicInteger threadSeq = new AtomicInteger(1);

    /**
     * Thread pool prefix.
     */
    private final String prefix;

    /**
     * Is it a daemon thread? The default is a user thread
     */
    @Getter
    private final Boolean daemon;

    /**
     * thread group.
     */
    @Getter
    private final ThreadGroup mGroup;

    /**
     * Instantiates a new Named thread factory.
     */
    public NamedThreadFactory() {
        this(null, false);
    }

    /**
     * Instantiates a new Named thread factory.
     *
     * @param prefix the prefix
     */
    public NamedThreadFactory(final String prefix) {
        this(prefix, false);
    }

    /**
     * Instantiates a new Named thread factory.
     *
     * @param prefix the prefix
     * @param daemon the daemon
     */
    public NamedThreadFactory(final String prefix, final Boolean daemon) {
        if (StringUtils.isEmpty(prefix)) {
            this.prefix = MessageFormat.format("pool-{0}-thread-", POOL_SEQ.getAndIncrement());
        } else {
            this.prefix = prefix + "-thread-";
        }

        this.daemon = null != daemon ? daemon : false;
        SecurityManager s = System.getSecurityManager();
        mGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    /**
     * Wrap the custom thread and pass traceId
     *
     * @param runnable the runnable
     * @param context  the context
     * @return the runnable
     */
    private static Runnable wrapRunnable(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }

//            TracerUtil.initTraceIdIfAbsent();
            try {
                runnable.run();
            } catch (Throwable throwable) {
                log.error("Thread pool uncaught " +throwable.getMessage(), throwable);
            } finally {
                MDC.clear();
            }
        };
    }

    /**
     * Constructs a new {@code Thread}.  Implementations may also initialize
     * priority, name, daemon status, {@code ThreadGroup}, etc.
     *
     * @param runnable a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to create a thread is rejected
     */
    @Override
    public Thread newThread(Runnable runnable) {
        String name = prefix + threadSeq.getAndIncrement();
        Thread thread = new Thread(mGroup, wrapRunnable(runnable, MDC.getCopyOfContextMap()), name, 0);
        thread.setDaemon(daemon);
        return thread;
    }
}