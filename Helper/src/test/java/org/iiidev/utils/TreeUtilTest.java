package org.iiidev.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.iiidev.entity.PmsCategory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
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

        List<PmsCategory> build = TreeUtil.build(0L, collect, in -> Pair.of(in.getCatId(), in.getParentCid()),
            PmsCategory::setChildCategories);

        List<PmsCategory> result = getPmsCategories(build);
        OutputStream outputStream = Files.newOutputStream(new File("result.json").toPath());
        IOUtils.write(JsonUtil.objToStr(result).getBytes(StandardCharsets.UTF_8), outputStream);
    }

    private static List<PmsCategory> getPmsCategories(List<PmsCategory> build) {
        List<PmsCategory> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            result.addAll(build);
        }
        return result;
    }


    @Test
    public void tiled01() throws IOException {
        OutputStream outputStream = new FileOutputStream("result.json");

        IOUtils.write("hello".getBytes(StandardCharsets.UTF_8), outputStream);
    }
}