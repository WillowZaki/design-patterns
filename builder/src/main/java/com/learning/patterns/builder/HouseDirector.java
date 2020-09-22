package com.learning.patterns.builder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author wangzhen
 * @date 2020/9/21
 */
@Slf4j
public final class HouseDirector {
    /**
     * CompletableFuture.runAsync()使用的线程池
     * private static final Executor asyncPool = useCommonPool ?
     * ForkJoinPool.commonPool() : new ThreadPerTaskExecutor();
     */
    public static House create(HouseBuilder builder) throws ExecutionException, InterruptedException {
        List<CompletableFuture<Void>> tasks = new ArrayList<>();
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        // 此处不能使用CompletableFuture，因为house还未实例化
        builder.buildBase();
        tasks.add(CompletableFuture.runAsync(builder::buildHead));
        tasks.add(CompletableFuture.runAsync(builder::buildBody));
        tasks.add(CompletableFuture.runAsync(builder::buildFoot));
        stopWatch.stop();
        log.info("耗时：[{}ms]", stopWatch.getTotalTimeMillis());
        // 也可使用join()
        CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0])).get();
        return builder.build();
    }
}
