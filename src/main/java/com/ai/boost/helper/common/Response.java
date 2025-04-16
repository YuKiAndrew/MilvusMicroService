package com.ai.boost.helper.common;

import com.ai.boost.helper.enumerate.ErrorCode;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Response {
    private boolean success;
    private String code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    private Response() {}

    public static Response success(String message) {
        Response r = new Response();
        r.setSuccess(true);
        r.setCode(ErrorCode.SUCCESS.getErrorCode());
        r.setMessage(message);
        return r;
    }

    public static Response success() {
        return success(ErrorCode.GENERALFAIL.getErrorMessage());
    }

    public static Response generalFail(String code, String message) {
        Response r = new Response();
        r.setSuccess(false);
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public static Response generalFail() {
        return generalFail(ErrorCode.GENERALFAIL.getErrorCode(), ErrorCode.GENERALFAIL.getErrorMessage());
    }

    public static Response generalFail(String message) {
        return generalFail(ErrorCode.SUCCESS.getErrorCode(), ErrorCode.GENERALFAIL.getErrorMessage());
    }

}
