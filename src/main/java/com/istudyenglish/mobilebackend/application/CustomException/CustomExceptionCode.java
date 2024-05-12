package com.istudyenglish.mobilebackend.application.CustomException;

public enum CustomExceptionCode {
    OtherErrors,
    DBErrors,
    LoginExist,
    LoginAndPasswordDoNotExist,
    PasswordDoNotExist,
    PasswordDoNotValid,
    TokenDoNotValid,
    TokenIsDead,
    TokenDoNotExist,
    TokenDoNotExistForStudent,
    AssignmentDoesNotBelongToStudent;


    private String message;

    public String getMessage(){return this.message;}
}
