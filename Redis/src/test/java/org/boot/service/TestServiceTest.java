package org.boot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * TestServiceTest
 *
 * @author IIIDelay
 * @createTime 2023年03月19日 12:34:00
 */
@SpringBootTest
@Component("org.boot")
public class TestServiceTest {
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void testPutCache() {
        System.out.println("getCacheNames : "+ cacheManager.getCacheNames());
        Cache rcTestDis = cacheManager.getCache("rcTestDis");
        rcTestDis.put("AAA", "BBB");

        redisTemplate.opsForValue().set("temp", "tempVal");
    }
}