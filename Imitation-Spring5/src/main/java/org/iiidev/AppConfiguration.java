package org.iiidev;

import org.spring.common.IIIDevComponentScan;

import java.util.HashMap;

/**
 * AppConfiguration
 *
 * @author IIIDelay
 * @createTime 2023年04月03日 20:37:00
 */
@IIIDevComponentScan("org.iiidev.service")
public class AppConfiguration {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "b");
    }
}
