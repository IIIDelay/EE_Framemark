package org.jvm;

/**
 * A
 *
 * @author IIIDelay
 * @createTime 2023年03月11日 14:43:00
 */
public class A {
    static int xx = 99;

    static {
        xx = 101;
    }

    public static void main(String[] args) {
        int a;
        System.out.println("a = " + xx);
    }
}
