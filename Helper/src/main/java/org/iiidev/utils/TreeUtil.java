package org.iiidev.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TreeUtil
 *
 * @author IIIDelay
 * @createTime 2023年04月01日 12:31:00
 */
public class TreeUtil {
    /**
     * build: list 转 tree
     *
     * @param rootId       rootId
     * @param inList       inList
     * @param childSetter  childSetter
     * @param idAndPidFunc id:pid
     * @return java.util.List<IN>
     */
    public static <IN> List<IN> build(Long rootId, List<IN> inList, Function<IN, Pair<Long, Long>> idAndPidFunc,
                                      BiConsumer<IN, List<IN>> childSetter) {
        if (rootId != null || CollectionUtils.isNotEmpty(inList)) {
            return inList.stream()
                    .filter(in -> rootId.equals(idAndPidFunc.apply(in).getRight()))
                    .peek(in -> {
                        List<IN> result = build(idAndPidFunc.apply(in).getLeft(), inList, idAndPidFunc, childSetter);
                        childSetter.accept(in, result);
                    })
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * tiled: 将tree平铺
     *
     * @param inList      inList
     * @param finalResult finalResult
     * @param childGetter childGetter
     * @return void
     */
    private static <IN> void childTiled(List<IN> inList, List<IN> finalResult, Function<IN, List<IN>> childGetter) {
        Optional.ofNullable(inList).orElse(Collections.emptyList()).stream().filter(Objects::nonNull).forEach(in -> {
            if (CollectionUtils.isNotEmpty(childGetter.apply(in))) {
                finalResult.addAll(childGetter.apply(in));
                childTiled(childGetter.apply(in), finalResult, childGetter);
            }
        });
    }

    public static <IN> List<IN> tiled(List<IN> inList, Function<IN, List<IN>> childGetter,
                                      Consumer<IN> childClear) {
        List<IN> result = new ArrayList<>();
        childTiled(inList, result, childGetter);
        // 子项处理完后，才能处理本身，否则重复添加
        inList.forEach(in -> {
            result.add(in);
            childClear.accept(in);
        });
        return result;
    }
}
