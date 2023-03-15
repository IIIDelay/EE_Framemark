package org.iiidev.utils;

import io.vavr.control.Try;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class YamlUtil {
    /**
     * loadYaml
     *
     * @param filePath filePath
     * @return Map<String, Map < String, String>>
     */
    public static Map<String, Map<String, String>> loadYaml(String filePath) {
        InputStream inputStream = YamlUtil.class.getClassLoader().getResourceAsStream(filePath);
        Yaml yaml = new Yaml();
        Map<String, Map<String, String>> yamlMap = yaml.load(inputStream);
        return yamlMap;
    }

    /**
     * loadResource
     *
     * @param in      in
     * @param mapping mapping
     * @return OUT
     */
    private static <IN, OUT> OUT loadResource(IN in, BiFunction<ClassLoader, IN, OUT> mapping) {
        ClassLoader classLoader = YamlUtil.class.getClassLoader();
        return Try.of(() -> mapping.apply(classLoader, in)).get();
    }

    /**
     * loadAllYaml
     *
     * @param filePath filePath
     * @return List<Object>
     */
    public static List<Object> loadAllYaml(String filePath) {
        InputStream inputStream = loadResource(filePath, ClassLoader::getResourceAsStream);
        Yaml yaml = new Yaml();
        Iterable<Object> objects = yaml.loadAll(inputStream);
        return StreamSupport.stream(objects.spliterator(), false).collect(Collectors.toList());
    }

    /**
     * loadAsYaml
     *
     * @param clazz    clazz
     * @param filePath filePath
     * @return OUT
     */
    public static <OUT> OUT loadAsYaml(Class<OUT> clazz, String filePath) {
        InputStream inputStream = YamlUtil.class.getClassLoader().getResourceAsStream(filePath);
        Yaml yaml = new Yaml();
        return yaml.loadAs(inputStream, clazz);
    }
}
