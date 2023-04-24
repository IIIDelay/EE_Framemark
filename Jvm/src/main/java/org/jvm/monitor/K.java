package org.jvm.monitor;

import cn.hutool.core.util.IdUtil;

import java.util.ArrayList;

/**
 * K
 *
 * @author IIIDelay
 * @createTime 2023年04月05日 22:27:00
 */
public class K {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("01");

        System.out.println("IdUtil.getSnowflakeNextId() = " + IdUtil.getSnowflakeNextId());
    }
}
