package org.iiidev.utils;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class TransferFiledUtil {
    public  static <IN,OUT>void setter(IN in, Consumer<IN> consumer) {
        Optional.ofNullable(in).ifPresent(value -> consumer.accept(value));
    }

    public static  <IN,OUT>void setter(IN in, OUT defaultIfNull, BiConsumer<IN,OUT> consumer) {
        Optional.ofNullable(in).ifPresent(value->consumer.accept(in, defaultIfNull));
    }

    public static <IN, OUT> void setterLevelIfNone(IN input, Function<IN, OUT> supplier, Consumer<OUT> consumer) {
        Optional.ofNullable(input).ifPresent(in ->
                Optional.ofNullable(supplier.apply(input)).ifPresent(out -> consumer.accept(out)));
    }

    public static <IN> void setterIfMatch(IN input, Predicate<IN> predicate, Consumer<IN> setter) {
        if (!predicate.test(input)) {
            return;
        }
        setter.accept(input);
    }
}
