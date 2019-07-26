package com.spring.demo.actor.demo2.worker;

import akka.actor.UntypedActor;
import com.spring.demo.actor.demo2.request.AkkaReq;
import com.spring.demo.actor.demo2.response.AkkaResp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component("workerActorDemo2")
@Scope("prototype")
public class WorkerActor extends UntypedActor {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof AkkaReq) {
            Response response = requestKnkm((AkkaReq) message);

            System.out.println("tread:" + Thread.currentThread() + ">>" + this.self().path() + " receive req:" + response.body().string());
            Thread.sleep(2000);
            AkkaResp akkaResp = new AkkaResp(((AkkaReq) message).getData());
            akkaResp.finish();
            getSender().tell(akkaResp, getSelf());
            //getContext().stop(getSelf());
        } else {
            System.out.println("worker unhandled");
            unhandled(message);
        }
    }

    private Response requestKnkm(AkkaReq message) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).build();
        String json = "{\"description\":\""+ message.getData()+"\",\"id\":4010}";
        RequestBody body = RequestBody.create(JSON, json);
        Headers.Builder headBuilder = new Headers.Builder();
        headBuilder
                .add("x-access-token","eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiwxNTYzNTI4ODY1NTQ5In0.5E5RODjuQjrMHoeX8K3VuNOEvaB5tApLksQIF7a5vUhW4CPOkIOwWbYzJOAGh0utHb-re8_dy0Qev0fB6T9jeQ")
                .add("Content-Type","application/json");
        Request request = new Request.Builder()
                .url("http://pds.aiqier.org/rest/orders/4010")
                .headers(headBuilder.build())
                .put(body).build();
        return client.newCall(request).execute();
    }
}