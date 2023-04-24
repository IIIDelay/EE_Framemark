package org.gof.constant;

import lombok.AllArgsConstructor;

/**
 * PayStartegreEnum
 *
 * @author IIIDelay
 * @createTime 2023年03月13日 22:11:00
 */
@AllArgsConstructor
public enum PayStartegreEnum {
    ALI_PAY("ali_pay"), WE_CHAT_PAY("wechat_pay"), UNION_PAY("union_pay");
    private String className;

    public String className() {
        return className;
    }
}
