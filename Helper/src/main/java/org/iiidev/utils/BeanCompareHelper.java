package org.iiidev.utils;

import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BeanCompareHelper {
    /**
     * 两个对象比较属性及其属性内容是否一致，用于迁移比较
     *
     * @param obj1 obj1
     * @param obj2 obj2
     * @return boolean
     */
    public static boolean compareObj(Object obj1, Object obj2) {
        // 如果地址相等则直接相等
        if (obj1 == obj2) {
            return true;
        }

        // 判断输入的类型是否都是基本类型
        if (isAllMatch(ClassUtils::isPrimitiveOrWrapper, obj1.getClass(), obj2.getClass())) {
            return Objects.equals(obj1, obj2);
        }

        try {
            // 如果不是基本类型，则判断对象的字段知否相等
            Field[] fields = obj1.getClass().getDeclaredFields();
            Map<String, Field> field02Map =
                    Arrays.stream(obj2.getClass().getDeclaredFields()).collect(Collectors.toMap(Field::getName,
                            Function.identity()));
            for (Field field : fields) {
                if (field02Map.get(field.getName()) != null) {
                    field.setAccessible(true);
                    field02Map.get(field.getName()).setAccessible(true);
                    if (Objects.equals(field, field02Map.get(field.getName())) && Objects.equals(field.get(obj1),
                            field02Map.get(field.getName()).get(obj2))) {
                        continue;
                    }
                    return false;
                } else {
                    return false;
                }
            }

            // 如果同时都是空对象，认为是True
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static <IN> Boolean isAllMatch(Predicate<IN> predicate, IN... ins) {
        return Optional.ofNullable(ins).map(booleans -> Arrays.stream(booleans).allMatch(predicate::test)).orElse(Boolean.FALSE);
    }
}
