package org.boot.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.support.SimpleCacheManager;

import java.util.Collection;

/**
 * AdaptorCacheManger
 *
 * @author IIIDelay
 * @createTime 2023年04月01日 23:18:00
 */
public class AdaptorCacheManger extends SimpleCacheManager {
    private boolean cacheEnable;

    @Override
    public Cache getCache(String name) {
        evictAllCache();
        return super.getCache(name);
    }

    /**
     * evictAllCache
     */
    @CachePut
    public void evictAllCache() {
        Collection<? extends Cache> caches = loadCaches();
        if (cacheEnable) {
            caches.clear();
        }
    }
}
