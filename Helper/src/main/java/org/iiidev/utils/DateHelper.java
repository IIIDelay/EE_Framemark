package org.iiidev.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

/**
 * DateHelper
 *
 * @author IIIDelay
 * @createTime 2023年03月12日 09:09:00
 */
public class DateHelper {
    /**
     * 默认时间处理格式
     */
    private static final String DATA_FORMAT_DEFAULT = "yyyy-MM-dd HH::mm::ss";

    /**
     * convertDate
     *
     * @param localDateTime localDateTime
     * @return Date
     */
    public static Date convertDate(LocalDateTime localDateTime) {
        return Optional.ofNullable(localDateTime).map(in -> {
            ZoneId zoneId = ZoneId.systemDefault();
            return Date.from(in.atZone(zoneId).toInstant());
        }).orElse(null);
    }

    /**
     * convertLocalDateTime
     *
     * @param date date
     * @return LocalDateTime
     */
    public static LocalDateTime convertLocalDateTime(Date date) {
        return Optional.ofNullable(date).map(in -> {
            Instant instant = in.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDateTime();
        }).orElse(null);
    }

    /**
     * format default: yyyy-MM-dd HH::mm::ss
     *
     * @param localDateTime localDateTime
     * @return String
     */
    public static String format(LocalDateTime localDateTime) {
        return format(localDateTime, DATA_FORMAT_DEFAULT);
    }

    /**
     * format
     *
     * @param localDateTime localDateTime
     * @param dataFormat    dataFormat
     * @return String
     */
    public static String format(LocalDateTime localDateTime, String dataFormat) {
        if (localDateTime == null || StringUtils.isEmpty(dataFormat)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dataFormat);
        return localDateTime.format(formatter);
    }
}
