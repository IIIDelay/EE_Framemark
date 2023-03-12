package org.iiidev.wrapper;

import java.util.function.Function;

/**
 * CheckedFunction
 *
 * @author IIIDelay
 * @createTime 2023年03月04日 17:46:00
 */
@FunctionalInterface
public interface CheckedFunction<IN, OUT, EX extends Throwable> {
    /**
     * apply
     *
     * @param in in
     * @return OUT
     * @throws EX
     */
    OUT apply(IN in) throws EX;

    static <IN, OUT, EX extends Throwable> Function<IN, OUT> exceptionWrapper(CheckedFunction<IN, OUT, EX> exFunc) {
        return in -> {
            try {
                return exFunc.apply(in);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }

    static <IN, OUT, EX extends Throwable> Function<IN, OUT> exceptionWrapper(Class<EX> exClazz, CheckedFunction<IN,
            OUT, EX> exFunc) {
        return in -> {
            try {
                return exFunc.apply(in);
            } catch (Throwable throwable) {
                try {
                    exClazz.cast(throwable);
                } catch (ClassCastException cce) {
                    throw new RuntimeException(cce);
                }
                throw new RuntimeException(throwable);
            }
        };
    }
}
