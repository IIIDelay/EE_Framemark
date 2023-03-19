package org.boot.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Lists;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.assertj.core.util.Sets;
import org.boot.configuration.property.ConfigProperties;
import org.iiidev.common.constant.CacheConstant;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

/**
 * CacheConfig
 *
 * @author IIIDelay
 * @createTime 2023年03月18日 23:08:00
 */
@EnableCaching
public class CacheConfig {
    /**
     * redisTemplate
     *
     * @param redisConnectionFactory redisConnectionFactory
     * @return RedisTemplate<Object, Object>
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * simpleLocalCacheManger : 单个本地缓存manager
     *
     * @return CacheManager
     */
    private CacheManager simpleLocalCacheManger() {
        CaffeineCacheManager ccm = new CaffeineCacheManager();
        ccm.setCaffeine(Caffeine.newBuilder().maximumSize(2000).expireAfterAccess(Duration.ofMinutes(30)));
        ccm.setCacheNames(Lists.newArrayList("slcm"));
        return ccm;
    }

    /**
     * compositeCacheManger : 管理cacheManger的容器
     *
     * @param redisConnectionFactory redisConnectionFactory
     * @return CacheManager
     */
    @Bean
    public CacheManager compositeCacheManger(RedisConnectionFactory redisConnectionFactory) {
        SimpleCacheManager slm = new SimpleCacheManager();
        CaffeineCache cache01 = new CaffeineCache("caffeineCache01",
                Caffeine.newBuilder().maximumSize(10).expireAfterAccess(Duration.ofSeconds(600)).build(), false);
        CaffeineCache cache02 = new CaffeineCache("caffeineCache02",
                Caffeine.newBuilder().maximumSize(10).expireAfterAccess(Duration.ofSeconds(600)).build(), false);
        Cache redis = redisCacheManager(redisConnectionFactory).getCache("rcTestDis");
        slm.setCaches(Lists.newArrayList(redis, cache01,cache02));
        return slm;
    }

    /**
     * redisCacheManager
     *
     * @param redisConnectionFactory redisConnectionFactory
     * @return CacheManager
     */
    private CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                // 设置缓存的默认过期时间
                .entryTtl(Duration.ofSeconds(180))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()))
                // 不缓存空值
                .disableCachingNullValues();

        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(config)
                .transactionAware()
                .initialCacheNames(Sets.newLinkedHashSet("rcTestDis"))
                .build();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(ConfigProperties configProperties) {
        RedisConfiguration rc = configProperties.redisConfiguration(CacheConstant.DeploymentMode.STAND_ALONE);

        GenericObjectPoolConfig pool = new GenericObjectPoolConfig();
        pool.setMaxIdle(10);
        pool.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(10).getSeconds());
        pool.setMinIdle(10);

        // LettucePoolingClientConfiguration配置
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder clientConfigurationBuild =
                LettucePoolingClientConfiguration.builder().commandTimeout(Duration.ofSeconds(120));
        clientConfigurationBuild.shutdownTimeout(Duration.ofSeconds(3));
        clientConfigurationBuild.poolConfig(pool);
        LettucePoolingClientConfiguration lpc = clientConfigurationBuild.build();

        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(rc, lpc);
        connectionFactory.afterPropertiesSet();

        return connectionFactory;
    }
}
