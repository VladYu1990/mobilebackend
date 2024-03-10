package com.istudyenglish.mobilebackend.application.CustomException;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends Exception {
    private String code;
    private String message;

    CustomException(){}

    public CustomException(CustomExceptionCode customExceptionCode){
        code = customExceptionCode.name();
        message = customExceptionCode.getMessage();
    }
}
