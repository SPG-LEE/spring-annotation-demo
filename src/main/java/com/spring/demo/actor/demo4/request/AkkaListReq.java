package com.spring.demo.actor.demo4.request;

import com.spring.demo.actor.demo4.response.AkkaResp;
import com.spring.demo.exception.ReadAkkaReqException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AkkaListReq {
    private List<AkkaOddReq> data;
    private List<AkkaResp> resp = new ArrayList<>();


    public AkkaListReq(List<AkkaOddReq> data) {
        this.data = data;
    }

    public String read() throws ReadAkkaReqException {
        if (!data.isEmpty()) {
            AkkaOddReq cur = data.get(0);
            data.remove(0);
            return cur.getData();
        }
        throw new ReadAkkaReqException("req empty,read error!");
    }

    public boolean hasNext() {
        return !data.isEmpty();
    }

    public void finish(AkkaResp r) {
        if (r.isFinish()) {
            resp.add(r);
        }
        resp.add(r);
    }

    public boolean hasFinish() {
        return data.size() == resp.size();
    }
}
