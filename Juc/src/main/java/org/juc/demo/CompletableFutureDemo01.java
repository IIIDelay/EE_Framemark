package org.juc.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * CompletableFutureDemo01
 *
 * @author IIIDelay
 * @createTime 2023年04月08日 20:47:00
 */
public class CompletableFutureDemo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<String> future = new CompletableFuture<>();

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(3, 6, 10L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(6));
        try {
            executorService.submit(getRunnable());
            executorService.submit(getRunnable());
            executorService.submit(getRunnable());
            executorService.submit(getRunnable());
            Future<String> submit = executorService.submit(() -> "invoke ...");
            System.out.println("submit.get() = " + submit.get());
        } finally {
            executorService.shutdown();
        }
        for (int i = 0; i < 40; i++) {
            System.out.println("main invoke i: "+i);
        }

    }

    private static Runnable getRunnable() {
        return () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName());
            }
        };
    }
}
