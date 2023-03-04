package org.iiidev.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * PropertiesHelper
 *
 * @author IIIDelay
 * @createTime 2023年03月04日 21:30:00
 */
public class PropertiesHelper {

    /**
     * loadProperties
     *
     * @param path   path
     * @param prefix prefix
     * @return Properties
     */
    public static Properties loadProperties(String path, String prefix) {
        InputStream is = PropertiesHelper.class.getClassLoader().getResourceAsStream(path);
        Properties ps = new Properties();
        ExceptionUtil.accept(is, IOException.class, ps::load);
        return ps;
    }
}
