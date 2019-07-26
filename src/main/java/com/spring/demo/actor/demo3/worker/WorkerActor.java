package com.spring.demo.actor.demo3.worker;

import akka.actor.UntypedActor;
import com.spring.demo.actor.demo3.request.AkkaReq;
import com.spring.demo.actor.demo3.response.AkkaResp;
import com.spring.demo.bean.AppResult;
import com.spring.demo.permission.AdminRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("workerActorDemo3")
@Scope("prototype")
public class WorkerActor extends UntypedActor {

    @Autowired
    private AdminRedisService adminRedisService;
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof AkkaReq) {
//            System.out.println("tread:" + Thread.currentThread() + ">>" + this.self().path() + " receive req:" + ((AkkaReq) message).getData());
            AkkaResp akkaResp = new AkkaResp(((AkkaReq) message).getData());
            Thread.sleep(1000);
            AppResult<String> valid = adminRedisService.getAdmin(((AkkaReq) message).getData());
            if (!valid.isSuccess()){
                System.out.println(valid.getCode());

            }
            akkaResp.finish();
            getSender().tell(akkaResp, getSelf());
            //getContext().stop(getSelf());
        } else {
            System.out.println("worker unhandled");
            unhandled(message);
        }
    }
}