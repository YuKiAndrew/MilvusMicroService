package com.ai.boost.helper.enumerate;

import lombok.Getter;

@Getter
public enum ErrorCode {
    SUCCESS("200","success"),
    GENERALFAIL("500","fail");

    private final String errorCode;

    private final String errorMessage;

    ErrorCode(String cerrorCode, String errorMessage){
        this.errorCode = cerrorCode;
        this.errorMessage = errorMessage;
    }
}
