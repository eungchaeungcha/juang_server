package com.eungchaeungcha.juang.common;

import lombok.Getter;

public class BusinessException extends RuntimeException{

    @Getter
    private final CommonErrorCode errorCode;

    public BusinessException(CommonErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
