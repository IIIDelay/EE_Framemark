package org.iiidev.utils;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionHelper {
    /**
     * readPartition
     *
     * @param inList  inList
     * @param mapping mapping
     * @return java.util.List<OUT>
     */
    public static <IN, OUT> List<OUT> readPartition(List<IN> inList, Function<List<IN>, List<OUT>> mapping) {
        return readPartition(inList, mapping, 50);
    }

    /**
     * writePartition : Default batchSize 50
     *
     * @param inList inList
     * @param peek   peek
     * @param <IN>
     */
    public static <IN> void writePartition(List<IN> inList, Consumer<List<IN>> peek) {
        writePartition(inList, peek, 50);
    }

    /**
     * writePartition
     *
     * @param inList    inList
     * @param peek      peek
     * @param batchSize batchSize
     */
    public static <IN> void writePartition(List<IN> inList, Consumer<List<IN>> peek, int batchSize) {
        flatPartition(inList, Pair.of(null, peek), batchSize);
    }

    /**
     * partition
     *
     * @param inList    inList
     * @param mapping   mapping
     * @param batchSize batchSize
     * @return java.util.List<OUT>
     */
    public static <IN, OUT> List<OUT> readPartition(List<IN> inList, Function<List<IN>, List<OUT>> mapping, int batchSize) {
        return flatPartition(inList, Pair.of(mapping, null), batchSize);
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

    private static <IN, OUT> List<OUT> flatPartition(List<IN> inList, Pair<Function<List<IN>, List<OUT>>, Consumer<List<IN>>> pair,
                                                     int batchSize) {
        Function<List<IN>, List<OUT>> leftFunc = pair.getLeft();
        Consumer<List<IN>> rightCm = pair.getRight();
        if (CollectionUtils.isEmpty(inList) && ObjectUtils.allNull(leftFunc, rightCm)) {
            return Collections.emptyList();
        }
        if (leftFunc != null) {
            return Lists.partition(inList, batchSize)
                    .stream()
                    .flatMap(ins -> Optional.ofNullable(leftFunc.apply(ins)).orElse(Collections.emptyList()).stream())
                    .collect(Collectors.toList());
        }
        Lists.partition(inList, batchSize)
                .forEach(ins -> rightCm.accept(ins));
        return Collections.emptyList();
    }

    private static <IN> BinaryOperator<IN> userLeft(boolean cover) {
        if (cover) {
            return (k1, k2) -> k2;
        }
        return (k1, k2) -> k1;
    }

}
