package org.boot.configuration;

import org.boot.configuration.manger.ConfigManger;
import org.iiidev.common.constant.CacheConstant;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;

/**
 * DistributedLockConfig
 *
 * @author IIIDelay
 * @createTime 2023年03月26日 20:47:00
 */
public class DistributedLockConfig {
    /**
     * redissonClient
     *
     * @param configManger configManger
     * @return RedissonClient
     */
    @Bean
    public RedissonClient redissonClient(ConfigManger configManger) {
        Config config = configManger.getConfigProperties()
                .redissionConfigChoose(CacheConstant.DeploymentMode.STAND_ALONE, null);
        return Redisson.create(config);
    }
}
