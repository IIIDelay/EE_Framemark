package org.iiidev.utils;

import java.util.function.BiFunction;

/**
 * ClassHelper
 *
 * @author IIIDelay
 * @createTime 2023年03月17日 11:47:00
 */
public class ClassHelper {
    /**
     * getForClassLoader
     *
     * @param getter getter
     * @param <OUT>
     * @return OUT
     */
    public static <OUT> OUT getForClassLoader(String in, BiFunction<ClassLoader, String,
            OUT> getter) {
        ClassLoader classLoader = ClassHelper.class.getClassLoader();
        // 注意泛型顺序
        return getter.apply(classLoader, in);
    }
}
