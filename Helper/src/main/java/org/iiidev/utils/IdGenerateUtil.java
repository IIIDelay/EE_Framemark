package org.iiidev.utils;

import java.util.UUID;

public class IdGenerateUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
