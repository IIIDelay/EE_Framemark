package org.list;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteListDemo01
 *
 * @author IIIDelay
 * @createTime 2023年03月14日 21:26:00
 */
public class CopyOnWriteListDemo01 {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        for (String s : list) {
            if ("2".equals(s)) {
                list.remove(s);
                list.add("22");
                list.add("23");
            }
            System.out.println("s = " + s);
        }

        System.out.println("list = " + list);
    }
}
