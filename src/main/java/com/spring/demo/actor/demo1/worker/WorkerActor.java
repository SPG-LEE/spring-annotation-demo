package com.spring.demo.actor.demo1.worker;

import akka.actor.UntypedActor;
import com.spring.demo.actor.demo1.request.AkkaReq;
import com.spring.demo.actor.demo1.response.AkkaResp;
import okhttp3.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component("workerActorDemo1")
@Scope("prototype")
public class WorkerActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof AkkaReq) {
            System.out.println(this.self().path().name() + " receive req:" + ((AkkaReq) message).getData());
//            Thread.sleep(3000);
//            AkkaResp akkaResp = new AkkaResp(((AkkaReq) message).getData());
//            akkaResp.finish();
//            getSender().tell(akkaResp, getSelf());
//            //getContext().stop(getSelf());
            System.out.println(requestSchool());
        } else {
            unhandled(message);
        }
    }
    private String requestSchool() throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).build();
//        String json = "{\"description\":\""+ message.getData()+"\",\"id\":4010}";
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("id","795");
        builder.add("identityId","37018120151019027X");
        builder.add("schoolLst","2");
        builder.add("verificationCode1","MX8N");
//        Headers.Builder headBuilder = new Headers.Builder();
//        headBuilder
//                .add("x-access-token","eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiwxNTYzNTI4ODY1NTQ5In0.5E5RODjuQjrMHoeX8K3VuNOEvaB5tApLksQIF7a5vUhW4CPOkIOwWbYzJOAGh0utHb-re8_dy0Qev0fB6T9jeQ")
//                .add("Content-Type","application/json");
        Request request = new Request.Builder()
                .url("http://39.98.221.1:8082/zqPrimary19/parent_chooseSchool")
//                .headers(headBuilder.build())
                .post(builder.build()).build();
        return client.newCall(request).execute().body().string();
    }
}