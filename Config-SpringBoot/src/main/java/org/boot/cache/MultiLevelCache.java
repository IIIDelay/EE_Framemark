package org.boot.cache;

import org.iiidev.common.exception.ServiceRuntimeException;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractValueAdaptingCache;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AdaptorCacheManger
 *
 * @author IIIDelay
 * @createTime 2023年04月01日 22:03:00
 */
public class MultiLevelCache extends AbstractValueAdaptingCache {
    private String name;

    private Cache localCache;

    private Cache remoteCache;

    private Class<?> valueType;

    public MultiLevelCache(String name, boolean allowNullValues, Cache localCache, Cache remoteCache,
                           Class<?> valueType) {
        super(allowNullValues);
        this.name = name;
        this.localCache = localCache;
        this.remoteCache = remoteCache;
        this.valueType = valueType;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        Lock lock = new ReentrantLock();
        try {
            Object lookup = lookup(key);
            if (lock != null) {
                return (T) lookup;
            }
            T call = valueLoader.call();
            put(key, call);
            return call;
        } catch (Exception ex) {
            throw new ServiceRuntimeException(ex);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void put(Object key, Object value) {
        if (!isAllowNullValues() && value == null) {
            return;
        }

        localCache.put(key, toStoreValue(value));

        if (value == null) {
            return;
        }

        // 入参传入rediscache已做config配置
        remoteCache.put(key, toStoreValue(value));
    }

    @Override
    public void evict(Object key) {
        remoteCache.evict(key);
        localCache.evict(key);
    }

    @Override
    public void clear() {
        remoteCache.clear();
        localCache.clear();
    }

    @Override
    protected Object lookup(Object key) {
        Object value = localCache.get(key, valueType);
        if (value != null) {
            return value;
        }

        value = remoteCache.get(key, valueType);
        if (value != null) {
            localCache.put(key, value);
        }
        return value;
    }
}
