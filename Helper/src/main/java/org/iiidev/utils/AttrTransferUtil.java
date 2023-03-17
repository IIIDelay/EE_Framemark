package org.iiidev.utils;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * AttrTransferUtil
 *
 * @author IIIDelay
 * @createTime 2023年03月13日 22:18:00
 */
public class AttrTransferUtil {
    /**
     * safeGetter
     *
     * @param in     in
     * @param getter getter
     * @return OUT
     */
    public static <IN, OUT> OUT safeGetter(IN in, Function<IN, OUT> getter) {
        return safeGetterElse(in, getter, null);
    }

    /**
     * safeSetter
     *
     * @param in       in
     * @param consumer consumer
     */
    public static <IN> void safeSetter(IN in, Consumer<IN> consumer) {
        Optional.ofNullable(in).ifPresent(consumer::accept);
    }

    /**
     * levelSafeGetter
     *
     * @param in        in
     * @param midGetter midGetter
     * @param getter    getter
     * @return OUT1
     */
    public static <IN, OUT, OUT1> OUT1 levelSafeGetter(IN in, Function<IN, OUT> midGetter, Function<OUT, OUT1> getter) {
        return safeGetter(safeGetter(in, midGetter), getter);
    }

    /**
     * safeGetterElse
     *
     * @param in      in
     * @param getter  getter
     * @param defualt defualt
     * @return OUT
     */
    public static <IN, OUT> OUT safeGetterElse(IN in, Function<IN, OUT> getter, OUT defualt) {
        return Optional.ofNullable(in).map(getter).orElse(defualt);
    }

    /**
     * safeGetter
     *
     * @param in  in
     * @param valid  valid
     * @param getter getter
     * @return OUT
     */
    public static <IN, OUT> OUT safeGetter(IN in, Predicate<IN> valid, Function<IN, OUT> getter) {
        return Optional.ofNullable(in).filter(valid).map(getter).orElse(null);
    }
}
