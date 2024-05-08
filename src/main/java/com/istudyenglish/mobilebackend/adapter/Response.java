package com.istudyenglish.mobilebackend.adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String request_status;
    private Object result_object;

    public Response(String request_status) {
        this.request_status = request_status;
    }

    public String getRequest_status() {
        return request_status;
    }

    public Object getResult_object() {
        return result_object;
    }

    @Override
    public String toString(){
        return request_status + ";" + result_object.toString();
    }
}
