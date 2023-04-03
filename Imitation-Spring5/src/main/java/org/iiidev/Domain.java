package org.iiidev;

import org.iiidev.common.resp.ServiceResponse;
import org.spring.IIIDevApplicationContext;

import java.lang.reflect.InvocationTargetException;

/**
 * Domain
 *
 * @author IIIDelay
 * @createTime 2023年04月03日 20:36:00
 */
public class Domain {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        IIIDevApplicationContext<AppConfiguration> iiiDevApplicationContext =
                new IIIDevApplicationContext<>(AppConfiguration.class);

        AppConfiguration xx = iiiDevApplicationContext.getBean("xx", AppConfiguration.class);
    }
}
