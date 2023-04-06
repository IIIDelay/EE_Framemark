package org.boot.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * AdaptorCacheManger
 *
 * @author IIIDelay
 * @createTime 2023年04月01日 23:18:00
 */
public class AdaptorCacheManger extends AbstractCacheManager {
    private ConcurrentMap<String, Cache> concurrentMap = new ConcurrentHashMap<>();

    public void putIfAbsent(String cacheName, Cache cache) {
        if (!concurrentMap.containsKey(cacheName)) {
            concurrentMap.put(cacheName, cache);
        }
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return concurrentMap.values();
    }
}
