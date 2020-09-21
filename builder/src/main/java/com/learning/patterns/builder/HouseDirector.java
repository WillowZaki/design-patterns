package com.learning.patterns.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author wangzhen
 * @date 2020/9/21
 */
public final class HouseDirector {

    public static House create(HouseBuilder builder) throws ExecutionException, InterruptedException {
        List<CompletableFuture<Void>> tasks = new ArrayList<>();
        // 此处不能使用CompletableFuture，因为house可能还未实例化
        builder.buildBase();
        tasks.add(CompletableFuture.runAsync(builder::buildHead));
        tasks.add(CompletableFuture.runAsync(builder::buildBody));
        tasks.add(CompletableFuture.runAsync(builder::buildFoot));
        CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0])).get();
        return builder.build();
    }
}
