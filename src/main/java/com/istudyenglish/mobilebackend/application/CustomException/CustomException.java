package com.istudyenglish.mobilebackend.application.CustomException;


public class CustomException extends Exception {
    private CustomExceptionCode code;
    private String message;

    public CustomException(CustomExceptionCode customExceptionCode){
        code = customExceptionCode;
        message = customExceptionCode.getMessage();
    }
}
