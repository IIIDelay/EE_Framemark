package org.jvm;

/**
 * B
 *
 * @author IIIDelay
 * @createTime 2023年03月11日 16:32:00
 */
public class B {
    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader ex = systemClassLoader.getParent();
        ClassLoader start = ex.getParent();

        ClassLoader classLoader = B.class.getClassLoader();
        System.out.println("systemClassLoader = " + systemClassLoader);
        System.out.println("ex = " + ex);
        System.out.println("start = " + start);
        System.out.println("classLoader = " + classLoader);
    }
}
