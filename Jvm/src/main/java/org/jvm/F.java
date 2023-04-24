package org.jvm;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * F
 *
 * @author IIIDelay
 * @createTime 2023年03月29日 22:50:00
 */
public class F {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object instance = new Object();
        new Thread(() -> {
            synchronized (instance) {
                System.out.println(" into synchronized block ...");
                System.out.println(ClassLayout.parseInstance(instance).toPrintable());
            }
        }).start();
    }
}
