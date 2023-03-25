package org.iiidev.common.constant;

import lombok.ToString;

/**
 * ErrorCodeEnum
 *
 * @author IIIDelay
 * @createTime 2023年03月25日 22:43:00
 */
@ToString
public enum ErrorCodeEnum {
    SUCCESS("resp-success-000", "ok!"),

    FAILED("resp-error-000","error!"),

    LIMIT_ERROR("limit-error-001", "限流异常");

    private String standCode;

    private String tip;

    ErrorCodeEnum(String standCode, String tip) {
        this.standCode = standCode;
        this.tip = tip;
    }

    public String standCode() {
        return standCode;
    }

    public String tip() {
        return tip;
    }
}
