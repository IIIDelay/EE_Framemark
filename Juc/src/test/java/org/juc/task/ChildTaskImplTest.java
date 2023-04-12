package org.juc.task;

import org.iiidev.common.context.LoginVal;
import org.iiidev.common.context.OauthContext;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;

/**
 * ChildTaskImplTest
 *
 * @author IIIDelay
 * @createTime 2023年04月12日 15:49:00
 */
@SpringBootTest
public class ChildTaskImplTest {
    public static void main(String[] args) {
        OauthContext.set(new LoginVal("xxBox"));
        LoginVal loginVal = OauthContext.get();
        System.out.println("当前线程值: " + loginVal);
        CompletableFuture.runAsync(() -> System.out.println(loginVal.getUsername()));
    }
}