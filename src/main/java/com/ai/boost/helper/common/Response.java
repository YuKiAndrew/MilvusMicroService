package com.ai.boost.helper.common;

import com.ai.boost.helper.enumerate.ErrorCode;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Response<T> {
    private boolean success;
    private String code;
    private String message;
    private T data;



    public Response success(String message, T data) {
        Response r = new Response();
        r.setSuccess(true);
        r.setCode(ErrorCode.SUCCESS.getErrorCode());
        r.setMessage(message);
        r.setData(data);

        return r;
    }

    public Response success( T data) {
        return success(ErrorCode.SUCCESS.getErrorMessage(), data);
    }

    public Response success() {
        return success(ErrorCode.GENERALFAIL.getErrorMessage(), null);
    }

    public Response generalFail(String code, String message) {
        Response r = new Response();
        r.setSuccess(false);
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public Response generalFail() {
        return generalFail(ErrorCode.GENERALFAIL.getErrorCode(), ErrorCode.GENERALFAIL.getErrorMessage());
    }

    public Response generalFail(String message) {
        return generalFail(ErrorCode.SUCCESS.getErrorCode(), ErrorCode.GENERALFAIL.getErrorMessage());
    }

}
