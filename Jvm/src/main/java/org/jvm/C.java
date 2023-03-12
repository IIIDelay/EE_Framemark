package org.jvm;

import sun.misc.Launcher;

import java.net.URL;

/**
 * C
 *
 * @author IIIDelay
 * @createTime 2023年03月11日 16:58:00
 */
public class C {
    public static void main(String[] args) {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println("urL = " + urL);
        }
    }
}
