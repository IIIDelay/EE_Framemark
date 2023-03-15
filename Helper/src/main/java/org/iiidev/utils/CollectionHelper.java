package org.iiidev.utils;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionHelper {
    /**
     * partition
     *
     * @param inList  inList
     * @param mapping mapping
     * @return java.util.List<OUT>
     */
    public static <IN, OUT> List<OUT> partition(List<IN> inList, Function<List<IN>, List<OUT>> mapping) {
        return partition(inList, mapping, 50);
    }

    private static <IN, OUT> List<OUT> flatPartition(List<IN> inList, Function<List<IN>, List<OUT>> mapping,
                                                     int batchSize) {
        return Lists.partition(inList, batchSize)
                .stream()
                .flatMap(ins -> Optional.ofNullable(mapping.apply(ins)).orElse(Collections.emptyList()).stream())
                .collect(Collectors.toList());
    }

    /**
     * partition
     *
     * @param inList    inList
     * @param mapping   mapping
     * @param batchSize batchSize
     * @return java.util.List<OUT>
     */
    public static <IN, OUT> List<OUT> partition(List<IN> inList, Function<List<IN>, List<OUT>> mapping, int batchSize) {
        if (inList == null || inList.isEmpty()) {
            return Collections.emptyList();
        }
        return flatPartition(inList, mapping, batchSize);
    }

    /**
     * toMap
     *
     * @param inList     inList
     * @param keyMapping keyMapping
     * @param valMapping valMapping
     * @param keyCover   keyCover
     * @return java.util.Map<LE, RE>
     */
    public static <IN, LE, RE> Map<LE, RE> toMap(List<IN> inList, Function<IN, LE> keyMapping,
                                                 Function<IN, RE> valMapping, boolean keyCover) {
        if (inList == null || inList.isEmpty()) {
            return Collections.emptyMap();
        }
        return inList.stream().collect(Collectors.toMap(keyMapping::apply, valMapping::apply, userLeft(keyCover)));
    }

    /**
     * toMap
     *
     * @param inList     inList
     * @param keyMapping keyMapping
     * @param valMapping valMapping
     * @return java.util.Map<LE, RE>
     */
    public static <IN, LE, RE> Map<LE, RE> toMap(List<IN> inList, Function<IN, LE> keyMapping,
                                                 Function<IN, RE> valMapping) {
        return toMap(inList, keyMapping, valMapping, false);
    }

    private static <IN> BinaryOperator<IN> userLeft(boolean cover) {
        if (cover) {
            return (k1, k2) -> k2;
        }
        return (k1, k2) -> k1;
    }

}
