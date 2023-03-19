package org.boot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
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

    @Test
    public void testPutCache() {
        System.out.println("getCacheNames : "+ cacheManager.getCacheNames());
        Cache rcTestDis = cacheManager.getCache("XXX");
        rcTestDis.put("AAA", "BBB");

    }
}