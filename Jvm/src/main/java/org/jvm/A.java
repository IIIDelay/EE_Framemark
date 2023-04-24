package org.jvm;

import cn.hutool.core.util.IdUtil;
import org.iiidev.utils.DateHelper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

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
        long snowflakeNextId = IdUtil.getSnowflakeNextId();
        System.out.println("snowflakeNextId = " + snowflakeNextId);
        long currentNum = 2199023255551l;
        long l = TimeUnit.SECONDS.toDays(2199023255551l);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(currentNum), ZoneId.systemDefault());
        String format = DateHelper.format(localDateTime);
        System.out.println("format = " + format);
        System.out.println("localDateTime = " + localDateTime);
        System.out.println("l = " + l);

        for (int i = 0; i < 10; i++) {
            long snowflakeNextId1 = IdUtil.getSnowflakeNextId();
            System.out.println("snowflakeNextId1 = " + snowflakeNextId1);
        }
    }
}
