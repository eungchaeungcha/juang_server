package com.eungchaeungcha.juang.common;


import org.springframework.http.HttpStatus;


public interface ErrorCode {
    String name();

    HttpStatus getHttpStatus();

    String getMessage();
}
