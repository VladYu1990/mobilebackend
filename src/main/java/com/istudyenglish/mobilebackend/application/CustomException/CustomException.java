package com.istudyenglish.mobilebackend.application.CustomException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomException extends Exception {
    private CustomExceptionCode code;
    private String message;

    public CustomException(CustomExceptionCode customExceptionCode){
        code = customExceptionCode;
        message = customExceptionCode.getMessage();
        log.error(message);
    }

    public CustomException(CustomExceptionCode customExceptionCode,String message){
        code = customExceptionCode;
        this.message = message;
        log.error(message);
    }
}
