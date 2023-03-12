package org.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * DoMain001
 *
 * @author IIIDelay
 * @createTime 2023年03月11日 10:06:00
 */
public class DoMain001 {
    static class One{}

    public static void main(String[] args) {
        List<One> list = new ArrayList<>();
        while (true) {
            list.add(new DoMain001.One());
        }
    }
}
