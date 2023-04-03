package org.boot.cache;

import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.redis.cache.RedisCache;

import java.util.concurrent.Callable;

/**
 * AdaptorCacheManger
 *
 * @author IIIDelay
 * @createTime 2023年04月01日 22:03:00
 */
public class MultiLevelCache extends AbstractValueAdaptingCache {
    private String name;

    private CaffeineCache localCache;

    private RedisCache remoteCache;

    public MultiLevelCache(boolean allowNullValues, CaffeineCache localCache, RedisCache remoteCache) {
        super(allowNullValues);
        this.localCache = localCache;
        this.remoteCache = remoteCache;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {

    }

    @Override
    public void evict(Object key) {

    }

    @Override
    public void clear() {

    }

    @Override
    protected Object lookup(Object key) {
        return null;
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return super.putIfAbsent(key, value);
    }

    @Override
    public boolean evictIfPresent(Object key) {
        return super.evictIfPresent(key);
    }

    @Override
    public boolean invalidate() {
        return super.invalidate();
    }
}
