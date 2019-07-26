package com.spring.demo.actor.demo4.request;

import lombok.Data;

@Data
public class AkkaEvenReq {
    private String data;

    public AkkaEvenReq(String data) {
        this.data = data;
    }
}
