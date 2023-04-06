package org.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * ApplicationBoot
 *
 * @author IIIDelay
 * @createTime 2023年03月10日 10:40:00
 */
@SpringBootApplication
public class ApplicationBoot {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationBoot.class, args);
        // String message = context.getMessage("test", null, new Locale("en"));
        Resource resources = context.getResource("file:org/boot/config/DefaultBeanInitiating.java");

        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();

        System.out.println("propertySources = " + propertySources);
        System.out.println("propertySources.get(\"ResourcePropertySource\") = " + propertySources.get(
                "class path resource [message.properties]").getProperty("aa"));

        System.out.println("resources.getFile().getName() = " + resources.getFile().getName());
    }
}
