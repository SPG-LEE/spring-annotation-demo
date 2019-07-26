package com.spring.demo.actor.demo4.request;

import lombok.Data;

@Data
public class AkkaOddReq {
    private String data;

    public AkkaOddReq(String data) {
        this.data = data;
    }
}
