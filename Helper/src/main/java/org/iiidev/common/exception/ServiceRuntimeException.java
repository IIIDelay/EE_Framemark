package org.iiidev.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iiidev.common.constant.ErrorCodeEnum;

/**
 * ServiceRuntimeException
 *
 * @author IIIDelay
 * @createTime 2023年03月18日 23:23:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRuntimeException extends RuntimeException{
    /**
     * code: -1 异常 | 0 ok
     */
    private int code;

    /**
     * 异常消息
     */
    private String message;

    /**
     * ErrorCode: 指定带有业务描述的异常Code
     */
    private ErrorCodeEnum ErrorCode;

    public ServiceRuntimeException(Throwable cause) {
        super(cause);
        build(-1, cause.getMessage(), null);
    }

    /**
     * of
     *
     * @return ServiceRuntimeException
     */
    public static ServiceRuntimeException of() {
        return build(-1, "接口调用异常!", null);
    }

    /**
     * of
     *
     * @return ServiceRuntimeException
     */
    public static ServiceRuntimeException of(String message) {
        return build(-1, message, null);
    }

    /**
     * of
     *
     * @param errorCode errorCode
     * @return ServiceRuntimeException
     */
    public static ServiceRuntimeException of(ErrorCodeEnum errorCode) {
        return build(-1, errorCode.tip(), errorCode);
    }

    private static ServiceRuntimeException build(int code, String msg, ErrorCodeEnum errorCode) {
        ServiceRuntimeException sre = new ServiceRuntimeException();
        sre.setCode(code);
        sre.setErrorCode(errorCode);
        sre.setMessage(msg);
        return sre;
    }
}
