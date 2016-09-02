package com.apppartner.androidprogrammertest.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by Kaushal.Mandayam on 8/31/2016.
 */
public class LoginResponse implements Serializable {

    @Expose
    String code;

    @Expose
    String message;

    @Expose
    float responseTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public float getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(float responseTime) {
        this.responseTime = responseTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}

