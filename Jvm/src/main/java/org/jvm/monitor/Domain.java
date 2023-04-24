package org.jvm.monitor;

import org.openjdk.jol.info.ClassLayout;

/**
 * Domain
 *
 * @author IIIDelay
 * @createTime 2023年03月27日 11:10:00
 */
public class Domain {
    public static void main(String[] args) {
        Object obj = new Object();
        System.out.println("obj.hashCode() = " + obj.hashCode());
        System.out.println("ClassLayout.parseInstance(obj) = " + ClassLayout.parseInstance(obj).toPrintable());
    }
}
