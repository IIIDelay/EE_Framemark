package org.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS
 *
 * @author IIIDelay
 * @createTime 2023年03月14日 15:41:00
 */
public class CAS {
    private int i = 0;
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void main(String[] args) {
        CAS cas = new CAS();
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ts.add(new Thread(()-> {
                for (int i1 = 0; i1 < 10000; i1++) {
                    cas.safeCount();
                    cas.count();
                    System.out.println("....");
                }
            }));
        }

        for (Thread t : ts) {
            t.start();
        }

        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("cas.i = " + cas.i);
        System.out.println("cas.atomicInteger.get() = " + cas.atomicInteger.get());

    }

    public void safeCount() {
        atomicInteger.getAndIncrement();
    }

    public void count() {
        i++;
    }
}
