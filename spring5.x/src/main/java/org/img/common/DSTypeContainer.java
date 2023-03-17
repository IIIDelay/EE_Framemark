package org.img.common;

import org.iiidev.utils.AttrTransferUtil;

/**
 * DSTypeContainer
 *
 * @author IIIDelay
 * @createTime 2023年03月17日 19:10:00
 */
public class DSTypeContainer {
    private static ThreadLocal<String> tl = new ThreadLocal<>();

    /**
     * set
     *
     * @param inputType inputType
     */
    public static void set(String inputType) {
        AttrTransferUtil.safeSetter(inputType, tl::set);
    }

    /**
     * get
     *
     * @return String
     */
    public static String get() {
        return tl.get();
    }

    /**
     * clear
     */
    public static void clear() {
        tl.remove();
    }

}
