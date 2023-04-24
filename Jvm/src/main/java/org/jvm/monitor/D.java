package org.jvm.monitor;

import cn.hutool.core.util.IdUtil;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * D
 *
 * @author IIIDelay
 * @createTime 2023年03月27日 22:38:00
 */
public class D {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        List<Long> countList = new CopyOnWriteArrayList<>();
        List<CompletableFuture> completableFutures = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 20; i++) {
            CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    long snowflakeNextId = IdUtil.getSnowflakeNextId();
                    countList.add(snowflakeNextId);
                    System.out.println("snowflakeNextId = " + snowflakeNextId);
                    System.out.println("Thread.currentThread().getId() = " + Thread.currentThread().getId());
                }
            }, executorService);
            completableFutures.add(completableFuture);
        }

        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]))
                .thenRun(() -> System.out.println(countList.size()));

        executorService.shutdown();
        System.out.println("end..");
    }
}
