package org.boot.service.impl;

import org.boot.service.InventoryService;
import org.iiidev.utils.AttrTransferUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
    private RedissonClient redissonClient;

    @Override
    public String sale() {
        RLock rLock = redissonClient.getLock("IIIDev-Lock");

        String message;
        try {
            rLock.lock();
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
            // 判断是只能删除属于自己的key，不能删除别人的
            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
        return message;
    }
}
