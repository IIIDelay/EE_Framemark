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
    ALI_PAY(""), WE_CHAT_PAY(""), UNION_PAY("");
    private String className;

    public String className() {
        return className;
    }
}
