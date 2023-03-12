package org.img.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * ServiceResponse
 *
 * @author IIIDelay
 * @createTime 2023年03月04日 17:12:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse<OUT> {
    private int code;
    private String msg;
    private OUT out;

    public static ServiceResponse<Void> ok() {
        return ok(200, "ok", null);
    }

    public static <OUT> ServiceResponse<OUT> ok(int code, String msg, OUT out) {
        ServiceResponse<OUT> sp = build(out);
        sp.setCode(code);
        sp.setMsg(msg);
        return sp;
    }

    public static <OUT> ServiceResponse<OUT> failed(int code, String msg) {
        ServiceResponse<OUT> sp = build(null);
        sp.setCode(code);
        sp.setMsg(msg);
        return sp;
    }

    private static <OUT> ServiceResponse<OUT> build(OUT input) {
        ServiceResponse<OUT> sp = new ServiceResponse<>();
        Optional.ofNullable(input).ifPresent(sp::setOut);
        return sp;
    }
}
