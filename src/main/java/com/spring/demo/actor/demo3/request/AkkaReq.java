package com.spring.demo.actor.demo3.request;

import lombok.Data;

@Data
public class AkkaReq {
    private String data;

    public AkkaReq(String data) {
        this.data = data;
    }
}
