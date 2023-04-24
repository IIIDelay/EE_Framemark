package org.jvm;

import io.vavr.control.Try;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * T
 *
 * @author IIIDelay
 * @createTime 2023年03月14日 12:52:00
 */
public class T {
    public static void main(String[] args) {
        Ticket target = new Ticket();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(target);
        executorService.execute(target);
        executorService.execute(target);
        executorService.execute(target);
        executorService.execute(target);

        executorService.shutdown();

        Try.run(() -> TimeUnit.SECONDS.sleep(10));
        System.out.println("end...");
    }
}
