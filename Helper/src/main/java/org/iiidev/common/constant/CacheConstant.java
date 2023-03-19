package org.iiidev.common.constant;

/**
 * CacheConstant
 *
 * @author IIIDelay
 * @createTime 2023年03月18日 23:21:00
 */
public interface CacheConstant {
    enum DeploymentMode {
        STAND_ALONE("standalone"), SENTINEL("sentinel"), CLUSTER("cluster");

        public String mode;

        DeploymentMode(String mode) {
            this.mode = mode;
        }
    }
}
