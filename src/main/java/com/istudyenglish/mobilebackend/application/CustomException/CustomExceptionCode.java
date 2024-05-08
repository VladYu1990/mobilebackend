package com.istudyenglish.mobilebackend.application.CustomException;

public enum CustomExceptionCode {
    OtherErrors,
    DBErrors,
    LoginExist,
    LoginAndPasswordDoNotExist,
    PasswordDoNotExist,
    PasswordDoNotValid,
    TokenIsDead,
    TokenDoNotExist,
    AssignmentDoesNotBelongToStudent;


    private String message;

    public String getMessage(){return this.message;}
}
