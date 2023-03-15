package org.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MapDemo02
 *
 * @author IIIDelay
 * @createTime 2023年03月15日 08:34:00
 */
public class MapDemo02 {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>(2);
        ConcurrentHashMap<String, Integer> map2 = new ConcurrentHashMap<>(2);
    }
}
