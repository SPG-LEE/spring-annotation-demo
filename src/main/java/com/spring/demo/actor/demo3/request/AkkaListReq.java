package com.spring.demo.actor.demo3.request;

import lombok.Data;

import java.util.List;

@Data
public class AkkaListReq {
    private List<String> data;

    public AkkaListReq(List<String> data) {
        this.data = data;
    }

    public String read() {
        String cur = data.get(0);
        if (!data.isEmpty()) {
            data.remove(0);
        }
        return cur;
    }

    public boolean hasNext(){
        return !data.isEmpty();
    }
}
