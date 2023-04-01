package org.iiidev.utils;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.iiidev.entity.PmsCategory;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TreeUtilTest
 *
 * @author IIIDelay
 * @createTime 2023年04月01日 12:46:00
 */
public class TreeUtilTest {
    @Test
    public void testBuild() throws Exception {
        InputStream is = ClassHelper.getForClassLoader("file/mockData.json", ClassLoader::getResourceAsStream);
        String cateJson = IOUtils.toString(is);

        List<PmsCategory> pmsCategories = JSONObject.parseObject(cateJson, new TypeReference<List<PmsCategory>>() {
        });
        List<PmsCategory> tiled =
        TreeUtil.tiled(pmsCategories, PmsCategory::getChildCategories,
                pmsCategory -> pmsCategory.setChildCategories(null));
        List<PmsCategory> collect = tiled.stream().distinct().map(pmsCategory -> {
            pmsCategory.setChildCategories(null);
            return pmsCategory;
        }).collect(Collectors.toList());
        System.out.println("tiled = " + JSONUtil.toJsonStr(tiled));

        List<PmsCategory> build = TreeUtil.build(0L, collect, in -> Pair.of(in.getCatId(), in.getParentCid()),
                PmsCategory::setChildCategories);
        System.out.println("JSON.toJSONString(build) = " + JSONUtil.toJsonStr(build));
    }


    private void tiled01(List<PmsCategory> pmsCategories, List<PmsCategory> result) {
        Optional.ofNullable(pmsCategories).orElse(Collections.emptyList()).stream().forEach(pmsCategory -> {
            Optional.ofNullable(pmsCategory).ifPresent(pmsc -> {
                result.add(pmsc);
                if (CollectionUtils.isNotEmpty(pmsc.getChildCategories())) {
                    result.addAll(pmsCategory.getChildCategories());
                    result.forEach(pms -> pms.setChildCategories(null));
                    this.tiled01(pmsCategory.getChildCategories(), result);
                }
            });
        });
    }
}