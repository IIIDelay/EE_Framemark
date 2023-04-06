package org.spring;

import io.vavr.control.Try;
import org.iiidev.utils.AttrTransferUtil;
import org.spring.common.IIIDevComponent;
import org.spring.common.IIIDevComponentScan;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * IIIDevApplicationContext
 *
 * @author IIIDelay
 * @createTime 2023年04月03日 20:36:00
 */
public class IIIDevApplicationContext<IN> {

    private Class<IN> clazz;

    public IIIDevApplicationContext(Class<IN> inClazz) {
        this.clazz = inClazz;
        // 获取class文件包含@Component注解的类信息
        componentScan(inClazz);

    }

    private <IN> void componentScan(Class<IN> inClazz) {
        if (inClazz.isAnnotationPresent(IIIDevComponentScan.class)) {
            IIIDevComponentScan annotation = inClazz.getAnnotation(IIIDevComponentScan.class);
            ClassLoader classLoader = IIIDevApplicationContext.class.getClassLoader();

            List<? extends Class<?>> ofComponentEleList =
                Arrays.stream(annotation.value()).filter(Objects::nonNull).flatMap(path -> {
                    URL resource = classLoader.getResource(path.replace(".", "/"));
                    File file = new File(resource.getFile());
                    return Arrays.stream(AttrTransferUtil.safeGetterElse(file, File::isDirectory, File::listFiles,
                        new File[]{}));
                }).map(file -> {
                    String absolutePath = file.getAbsolutePath();
                    String packagePath = absolutePath.substring(absolutePath.indexOf("org"), absolutePath.indexOf(
                        ".class"
                    )).replace("\\", ".");
                    return Try.of(() -> classLoader.loadClass(packagePath)).get();
                }).filter(clazz -> clazz.isAnnotationPresent(IIIDevComponent.class)).collect(Collectors.toList());
        }
    }

    /**
     * getBean
     *
     * @param name name
     * @return Object
     */
    public Object getBean(String name) {
        return null;
    }

    /**
     * getBean
     *
     * @param name    name
     * @param inClazz inClazz
     * @param <OUT>
     * @return OUT
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public <OUT> OUT getBean(String name, Class<OUT> inClazz) throws InstantiationException, IllegalAccessException,
        NoSuchMethodException, InvocationTargetException {
        return inClazz.newInstance();
    }

}


