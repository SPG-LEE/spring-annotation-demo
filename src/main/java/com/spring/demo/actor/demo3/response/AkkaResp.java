package com.spring.demo.actor.demo3.response;

import lombok.Data;

@Data
public class AkkaResp {
    private String data;

    private Status status = Status.PENDING;


    public AkkaResp(String data) {
        this.data = data;
    }

    public enum Status {
        PENDING, DONE;
    }

    public void finish() {
        status = Status.DONE;
    }
    public boolean isFinish() {
        return this.status.equals(Status.DONE);
    }
}
