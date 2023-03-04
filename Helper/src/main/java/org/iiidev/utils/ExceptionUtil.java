package org.iiidev.utils;

import org.iiidev.wrapper.CheckedConsumer;
import org.iiidev.wrapper.CheckedFunction;

import java.util.Optional;

/**
 * ExceptionUtil
 *
 * @author IIIDelay
 * @createTime 2023年03月04日 21:33:00
 */
public class ExceptionUtil {
    /**
     * mapping
     *
     * @param in    in
     * @param clazz clazz
     * @param fc    fc
     * @return OUT
     */
    public static <IN, OUT, EX extends Throwable> OUT mapping(IN in, Class<EX> clazz, CheckedFunction<IN, OUT, EX> fc) {
        return Optional.ofNullable(in).map(CheckedFunction.exceptionWrapper(clazz, fc)::apply).orElse(null);
    }

    public static <IN, EX extends Throwable> void accept(IN in, Class<EX> clazz, CheckedConsumer<IN, EX> consumer) {
        Optional.ofNullable(in).ifPresent(CheckedConsumer.exceptionWrapper(clazz,consumer)::accept);
    }
}
