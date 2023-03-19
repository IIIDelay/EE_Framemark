package org.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * TestService
 *
 * @author IIIDelay
 * @createTime 2023年03月19日 12:30:00
 */
@Service
public class TestService {
    @Autowired
    private CacheManager cacheManager;

    public void putCache() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        System.out.println("cacheNames = " + cacheNames);
    }
}
