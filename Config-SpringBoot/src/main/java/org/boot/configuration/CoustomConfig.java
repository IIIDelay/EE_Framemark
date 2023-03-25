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
}
