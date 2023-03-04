package org.iiidev.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.iiidev.wrapper.CheckedFunction;

/**
 * JsonUtil
 *
 * @author IIIDelay
 * @createTime 2023年03月04日 17:37:00
 */
public class JsonUtil {
    /**
     * ObjectToString
     *
     * @param in in
     * @return String
     */
    public static <IN> String objToStr(IN in) {
        ObjectMapper objectMapper = new ObjectMapper();
        return build(in, JsonProcessingException.class, objectMapper::writeValueAsString);
    }

    public static <OUT> OUT StrToObj(String inStr, Class<OUT> inClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        return build(inStr, JsonProcessingException.class, in -> objectMapper.readValue(in, inClass));
    }

    public static <OUT> OUT StrToFormalObj(String inStr, TypeReference<OUT> typeReference) {
        ObjectMapper objectMapper = new ObjectMapper();
        return build(inStr, JsonProcessingException.class, in -> objectMapper.readValue(in, typeReference));
    }


    private static <IN, OUT, EX extends Throwable> OUT build(IN in, Class<EX> ex,
                                                                       CheckedFunction<IN, OUT, EX> function) {
        return CheckedFunction.exceptionWrapper(ex, function).apply(in);
    }
}
