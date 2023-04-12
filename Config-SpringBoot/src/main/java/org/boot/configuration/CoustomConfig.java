package org.boot.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * CoustomConfig
 *
 * @author IIIDelay
 * @createTime 2023年03月25日 23:14:00
 */
@Getter
@Component
public class CoustomConfig {
    @Value("${swagger.docket.show.enable:false}")
    private boolean enableSwagger;

    @Value("${default.corePoolSize:0}")
    private int corePoolSize;

    @Value("${default.maxPoolSize:0}")
    private int maxPoolSize;

    @Value("${default.keepAliveSeconds:0}")
    private int keepAliveSeconds;

    @Value("${default.queueCapacity:0}")
    private int queueCapacity;
}
