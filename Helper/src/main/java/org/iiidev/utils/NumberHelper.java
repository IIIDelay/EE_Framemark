package org.iiidev.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Optional;

public class NumberHelper {
    /**
     * coverToDouble
     *
     * @param inputStr inputStr
     * @return Double
     */
    public static Double coverToDouble(String inputStr) {
        return AttrTransferUtil.safeGetter(inputStr, NumberUtils::isDigits, Double::valueOf);
    }

    public static Double coverToDouble(String inputStr, Double defaultVal) {
        return Optional.ofNullable(AttrTransferUtil.safeGetter(inputStr, NumberUtils::isDigits, Double::valueOf)).orElse(defaultVal);
    }
}
