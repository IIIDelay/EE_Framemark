package org.iiidev.utils;

import java.lang.reflect.Field;
import java.util.Map;

public class EntityTransferUtil {
    public static void main(String[] args) {
    }

    public static <OUT> OUT mapToObj(Map<String, String> inputMap, Class<OUT> outClass) {
        try {
            Field[] fields = outClass.getDeclaredFields();
            OUT out = outClass.newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                if (inputMap.get(field.getName()) != null) {
                    field.set(out, inputMap.get(field.getName()));
                }
            }
            return out;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
