package org.jvm;

import java.util.concurrent.TimeUnit;

/**
 * Domain
 *
 * @author IIIDelay
 * @createTime 2023年03月09日 21:45:00
 */
public class Domain {
    private static long count = 0;

    public static void main(String[] args) throws InterruptedException {
        add();
        TimeUnit.MINUTES.sleep(1);
    }

    public static void add() throws InterruptedException {
        int b[][] = {{0, 1, 2}, {22, 33, 44}, {0, 1, 2}};
        for (int[] ints : b) {
            for (int anInt : ints) {
                System.out.println("anInt = " + anInt);
            }
        }
    }
}
