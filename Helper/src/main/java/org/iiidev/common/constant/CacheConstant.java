package org.iiidev.common.constant;

/**
 * CacheConstant
 *
 * @author IIIDelay
 * @createTime 2023年03月18日 23:21:00
 */
public interface CacheConstant {
    /**
     * 分布式锁相关属性
     */
    interface DistrubitedLock {
        String MODE_REDIS = "redis";
        String MODE_ZOOKPEEPER = "zookeeper";
    }

    /**
     * 部署模式
     */
    enum DeploymentMode {
        STAND_ALONE("standalone"), SENTINEL("sentinel"), CLUSTER("cluster");

        /**
         * 模式名称
         */
        public String mode;

        DeploymentMode(String mode) {
            this.mode = mode;
        }
    }
}
