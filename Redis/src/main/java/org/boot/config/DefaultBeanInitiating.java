package org.boot.config;

import org.boot.configuration.CacheConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * DefaultBeanInitiating
 *
 * @author IIIDelay
 * @createTime 2023年03月19日 12:54:00
 */
@Configuration
@Import({CacheConfig.class})
public class DefaultBeanInitiating {

}
