package com.istudyenglish.mobilebackend;

import lombok.Getter;

@Getter
public class CustomException extends Exception {

    private String message;

    public CustomException() {
        this.message = "произошла какая-то херня";
    }
    @Override
    public String getMessage(){
        return  this.message;
    }


}
