package org.boot.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * DefaultBeanInitiating
 *
 * @author IIIDelay
 * @createTime 2023年03月23日 00:22:00
 */
@Configuration
@PropertySource("classpath:message.properties")
public class DefaultBeanInitiating {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("message");
        return messageSource;
    }


}
