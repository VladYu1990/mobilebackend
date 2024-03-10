package com.istudyenglish.mobilebackend.application.CustomException;

public enum CustomExceptionCode {
    LoginExist,
    LoginDoNotExist,
    PasswordDoNotExist,
    PasswordDoNotValid,
    TokenIsDead,
    TokenDoNotExist;

    private String message;

    public String getMessage(){return this.message;}
}
