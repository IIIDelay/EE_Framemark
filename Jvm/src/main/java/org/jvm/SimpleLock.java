package org.jvm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * SimpleLock
 *
 * @author IIIDelay
 * @createTime 2023年03月14日 16:24:00
 */
public class SimpleLock {
    private AtomicReference<Thread> af = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println("myLock thread.getName() = " + thread.getName());
        while (!af.compareAndSet(null, thread)) {
            System.out.println("自旋中 thread.getName() = " + thread.getName());
        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println("myUnLock thread.getName() = " + thread.getName());
        af.compareAndSet(thread, null);
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleLock simpleLock = new SimpleLock();
        new Thread(()->{
            try {
                simpleLock.myLock();
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                simpleLock.myUnLock();
            }
        }, "AAA: ").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            try {
                simpleLock.myLock();
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                simpleLock.myUnLock();
            }
        }, "BBB: ").start();
    }
}
