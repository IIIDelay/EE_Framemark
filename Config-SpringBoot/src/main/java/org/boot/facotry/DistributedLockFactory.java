package org.boot.facotry;

import cn.hutool.core.util.IdUtil;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import org.boot.lock.RedisDistributedLock;
import org.iiidev.common.constant.CacheConstant;
import org.iiidev.common.exception.ServiceRuntimeException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.locks.Lock;

/**
 * DistributedLockFactory
 *
 * @author IIIDelay
 * @createTime 2023年03月26日 14:41:00
 */
@Component
public class DistributedLockFactory implements ApplicationContextAware {

    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.stringRedisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    }

    private String lockName;

    /**
     * buildDistributedLock : 根据类型构造对应分布式锁
     *
     * @param lockType lockType
     * @return Lock
     */
    public Lock buildDistributedLock(String lockType) {
        return Match(lockType).of(Case($(Objects::isNull), () -> null),
                Case($(CacheConstant.DistrubitedLock.MODE_REDIS), () -> {
                    this.lockName = "iiidevRedisLock";
                    return new RedisDistributedLock(stringRedisTemplate, lockName,
                            String.valueOf(IdUtil.getSnowflakeNextId()));
                }),
                Case($(CacheConstant.DistrubitedLock.MODE_ZOOKPEEPER), () -> {
                    this.lockName = "iiidevZookpeeperLock";
                    // TODO: 未实现。。。
                    throw ServiceRuntimeException.of("未实现");
                }),
                Case($(), () -> null)
        );
    }
}
