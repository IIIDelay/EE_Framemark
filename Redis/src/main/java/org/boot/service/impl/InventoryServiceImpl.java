package org.boot.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import org.boot.service.InventoryService;
import org.iiidev.common.annotation.RedisLimit;
import org.iiidev.utils.AttrTransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * InventoryServiceImpl
 *
 * @author IIIDelay
 * @createTime 2023年03月23日 20:15:00
 */
@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private CacheManager cacheManager;

    @Override
    @RedisLimit(key = "classRedisList")
    public String sale() {
        DataSource dataSource = new DruidDataSource();

        Lock lock = new ReentrantLock();
        String message;
        try {
            lock.lock();

            // 1. 查询库存信息
            Integer inventory = AttrTransferUtil.safeGetterElse((String) redisTemplate.opsForValue().get("inventory"),
                    Integer::valueOf, 0);

            // 2. 判断库存是否还有
            // 3. 扣减库存，每次减一
            if (inventory > 0) {
                redisTemplate.opsForValue().set("inventory", String.valueOf(--inventory));
                message = "库存扣减成功！";
            } else {
                message = "库存清空，扣减失败qaq";
            }
        } finally {
            lock.unlock();
        }
        return message;
    }
}
