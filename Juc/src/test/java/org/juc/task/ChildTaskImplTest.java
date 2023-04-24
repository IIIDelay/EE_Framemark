package org.juc.task;

import org.boot.configuration.TaskConfig;
import org.iiidev.common.context.LoginVal;
import org.iiidev.common.context.OauthContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;

/**
 * ChildTaskImplTest
 *
 * @author IIIDelay
 * @createTime 2023年04月12日 15:49:00
 */
@SpringBootTest
public class ChildTaskImplTest {
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    public void test() {
        OauthContext.set(new LoginVal("xxBox"));
        LoginVal loginVal = OauthContext.get();
        System.out.println("当前线程值: " + Thread.currentThread().getName() + loginVal);
        CompletableFuture.runAsync(() -> System.out.println("子线程" + Thread.currentThread().getName() + OauthContext.get()),
            threadPoolTaskExecutor);
    }
}