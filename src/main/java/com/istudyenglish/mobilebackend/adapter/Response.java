package com.istudyenglish.mobilebackend.adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String request_status;
    private Object result;

    public String getRequest_status() {
        return request_status;
    }

    public Object getResult() {
        return result;
    }
}
