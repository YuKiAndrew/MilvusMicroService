package com.ai.boost.helper.common;

import com.ai.boost.helper.enumerate.ErrorCode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class GlobalException extends RuntimeException{

    private String errCode = ErrorCode.GENERALFAIL.getErrorCode();


    private String message;

    public GlobalException(ErrorCode errorCode) {
        this.errCode = errorCode.getErrorCode();
        this.message = errorCode.getErrorMessage();
    }

    public GlobalException(ErrorCode errorCode, String message){
        this.errCode = errorCode.getErrorCode();
        this.message = errorCode.getErrorMessage();
    }

    public GlobalException(String message, String code) {
        this.message = message;
        this.errCode = code;
    }


}
