package org.jvm;

import lombok.SneakyThrows;

/**
 * Ticket
 *
 * @author IIIDelay
 * @createTime 2023年03月14日 12:55:00
 */
public class Ticket implements Runnable {
    private int count = 10000;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            if (count <= 0) {
                return;
            }
            count--;
            System.out.println(Thread.currentThread().getName() + "剩余: " + count + " 张票");
        }
    }
}
