package org.boot.configuration.manger;

import lombok.Getter;
import org.boot.configuration.CoustomConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ConfigManger
 *
 * @author IIIDelay
 * @createTime 2023年03月25日 23:18:00
 */
@Component
@Getter
public class ConfigManger {
    @Autowired
    private CoustomConfig coustomConfig;
}
