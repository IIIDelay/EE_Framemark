package org.iiidev.common.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iiidev.common.constant.ErrorCodeEnum;

/**
 * ServiceResponse
 *
 * @author IIIDelay
 * @createTime 2023年03月18日 23:22:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse<OUT> {

    private ErrorCodeEnum codeEnum;

    private OUT out;

    /**
     * ok
     *
     * @param <OUT>
     * @return ServiceResponse<OUT>
     */
    public static <OUT>ServiceResponse<OUT> ok() {
        return build(ErrorCodeEnum.SUCCESS, null);
    }

    /**
     * ok
     *
     * @param out   out
     * @param <OUT>
     * @return ServiceResponse<OUT>
     */
    public static <OUT>ServiceResponse<OUT> ok(OUT out) {
        return build(ErrorCodeEnum.SUCCESS, out);
    }

    /**
     * ok
     *
     * @param codeEnum codeEnum
     * @param out      out
     * @param <OUT>
     * @return ServiceResponse<OUT>
     */
    public static <OUT>ServiceResponse<OUT> ok(ErrorCodeEnum codeEnum, OUT out) {
        return build(codeEnum, out);
    }

    /**
     * fail
     *
     * @param <OUT>
     * @return ServiceResponse<OUT>
     */
    public static <OUT>ServiceResponse<OUT> fail() {
        return build(ErrorCodeEnum.FAILED, null);
    }

    /**
     * fail
     *
     * @param codeEnum codeEnum
     * @param <OUT>
     * @return ServiceResponse<OUT>
     */
    public static <OUT>ServiceResponse<OUT> fail(ErrorCodeEnum codeEnum) {
        return build(codeEnum, null);
    }

    private static <OUT>ServiceResponse<OUT> build(ErrorCodeEnum codeEnum, OUT out) {
        ServiceResponse<OUT> serviceResponse = new ServiceResponse<>();
        serviceResponse.setCodeEnum(codeEnum);
        serviceResponse.setOut(out);
        return serviceResponse;
    }
}
