package com.eungchaeungcha.juang.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    INVALID_USERNAME_AND_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid username and password"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    FAMILY_NOT_FOUND(HttpStatus.NOT_FOUND, "Family not found"),
    ALREADY_EXISTS_USERNAME(HttpStatus.BAD_REQUEST, "Already username exists"),
    CHARACTER_NOT_FOUND(HttpStatus.NOT_FOUND, "Character not found");


    private final HttpStatus httpStatus;
    private final String message;
}