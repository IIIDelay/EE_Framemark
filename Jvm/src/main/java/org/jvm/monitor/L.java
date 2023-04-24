package org.jvm.monitor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * L
 *
 * @author IIIDelay
 * @createTime 2023年04月09日 15:05:00
 */
public class L {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> ticket.sale()).start();
        }
    }
}

class Ticket {
    private int count = 1000;

    private Lock lock = new ReentrantLock();

    public void sale() {
        while (true) {
            try {
                lock.lock();
                if (count <= 0) {
                    System.out.println("票已售罄.");
                    break;
                }
                --count;
                System.out.println("当前线程是: " + Thread.currentThread().getName() + " 还剩下票: " + count + " 张");
            } finally {
                lock.unlock();
            }
        }

    }
}
