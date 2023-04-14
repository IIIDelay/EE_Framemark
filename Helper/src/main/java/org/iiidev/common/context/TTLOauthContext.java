package org.iiidev.common.context;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.concurrent.CompletableFuture;

/**
 * TTLOauthContext
 *
 * @author IIIDelay
 * @createTime 2023年04月12日 16:36:00
 */
public class TTLOauthContext {

    private static final TransmittableThreadLocal<LoginVal> loginValThreadLocal = new TransmittableThreadLocal<>();

    public static LoginVal get() {
        return loginValThreadLocal.get();
    }

    public static void set(LoginVal loginVal) {
        loginValThreadLocal.set(loginVal);
    }

    public static void clear() {
        loginValThreadLocal.remove();
    }

    /**
     * main 验证
     *
     * @param args args
     */
    public static void main(String[] args) {
        TTLOauthContext.set(new LoginVal("关羽"));
        System.out.println(Thread.currentThread().getName()+" 父线程获取的值为: "+TTLOauthContext.get());
        CompletableFuture.runAsync(() -> System.out.println(Thread.currentThread().getName() + " 父线程获取的值为: " + TTLOauthContext.get()));
    }
}
