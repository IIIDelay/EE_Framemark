package org.jvm.monitor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * H
 *
 * @author IIIDelay
 * @createTime 2023年03月30日 14:16:00
 */
public abstract class H {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
    }
}
