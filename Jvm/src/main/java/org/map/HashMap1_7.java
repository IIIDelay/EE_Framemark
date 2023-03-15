package org.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * HashMap1_7
 *
 * @author IIIDelay
 * @createTime 2023年03月14日 19:35:00
 */
public class HashMap1_7 {
    public static final String NAME = "小明";
    private static Map<String, Integer> cMap = new ConcurrentHashMap<>();
    private static Map<String, Integer> map = new HashMap<>();
    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        cMap.put(NAME, 0);

        Thread a = new Thread(HashMap1_7::run2, " A ");

        Thread b = new Thread(HashMap1_7::run2, " B ");

        a.start();
        b.start();

        a.join();
        b.join();

        System.out.println("cMap = " + atomicInteger.get());
    }

    private static void run2() {
        for (int i = 0; i < 10000; i++) {
            while (true) {
                Integer integer = cMap.get(NAME);
                if (atomicInteger.compareAndSet(integer, ++integer)) {
                    cMap.put(NAME, integer);
                    break;
                }
            }
        }
    }
}
