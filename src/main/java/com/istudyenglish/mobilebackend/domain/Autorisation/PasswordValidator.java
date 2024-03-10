package com.istudyenglish.mobilebackend.domain.Autorisation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class PasswordValidator {

    private boolean valid = false;
    private String message;
    private StringBuilder stringBuilder = new StringBuilder();
    private String passwordForValidation;

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }

    public void validatePassword(String password){
        this.passwordForValidation = password;

        checkLength();
        checkSpecialCharacters();
        message = stringBuilder.toString();
    }

    private void checkLength(){
        if(6<=passwordForValidation.length() & passwordForValidation.length()<=20){
            valid = true;
            stringBuilder.append(" length is true,");
        }
        else {
            valid = true;
            stringBuilder.append(" length is false,");
        }

    }

    private void checkSpecialCharacters(){
        if(passwordForValidation.contains("?") ||
                passwordForValidation.contains(",") ||
                passwordForValidation.contains(";") ||
                passwordForValidation.contains("'")){
            valid = false;
            stringBuilder.append(" the password contains special characters, ");
        }
        else {
            valid = true;
            stringBuilder.append(" the password contains special characters,");
        }
    }


}
