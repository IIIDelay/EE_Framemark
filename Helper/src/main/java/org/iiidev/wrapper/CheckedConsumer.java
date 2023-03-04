package org.iiidev.wrapper;

import java.util.function.Consumer;

/**
 * CheckedConsumer
 *
 * @author IIIDelay
 * @createTime 2023年03月04日 17:48:00
 */
@FunctionalInterface
public interface CheckedConsumer<IN, EX extends Throwable> {
    /**
     * accept
     *
     * @param in in
     * @throws EX
     */
    void accept(IN in) throws EX;

    /**
     * exceptionWrapper
     *
     * @param clazz    clazz
     * @param consumer consumer
     * @return Consumer<IN>
     */
    static <IN, EX extends Throwable> Consumer<IN> exceptionWrapper(Class<EX> clazz, CheckedConsumer<IN, EX> consumer) {
        return in -> {
            try {
                consumer.accept(in);
            } catch (Throwable e) {
                try {
                    clazz.cast(e);
                } catch (ClassCastException cce) {
                    throw new RuntimeException(cce);
                }
            }
        };
    }
}
