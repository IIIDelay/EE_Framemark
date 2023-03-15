package org.jvm;

/**
 * T
 *
 * @author IIIDelay
 * @createTime 2023年03月14日 12:52:00
 */
public class T {
    public static void main(String[] args) {
        Ticket target = new Ticket();
        new Thread(target, "A: ").start();
        new Thread(target, "B: ").start();
        new Thread(target, "C: ").start();
        new Thread(target, "D: ").start();
        new Thread(target, "E: ").start();
        new Thread(target, "F: ").start();

        System.out.println("end...");
    }
}
