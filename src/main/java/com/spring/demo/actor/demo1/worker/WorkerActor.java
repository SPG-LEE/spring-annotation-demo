package com.spring.demo.actor.demo1.worker;

import akka.actor.UntypedActor;
import com.spring.demo.actor.demo1.request.AkkaReq;
import com.spring.demo.actor.demo1.response.AkkaResp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("workerActorDemo1")
@Scope("prototype")
public class WorkerActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof AkkaReq) {
            System.out.println(this.self().path().name() + " receive req:" + ((AkkaReq) message).getData());
            Thread.sleep(3000);
            AkkaResp akkaResp = new AkkaResp(((AkkaReq) message).getData());
            akkaResp.finish();
            getSender().tell(akkaResp, getSelf());
            //getContext().stop(getSelf());
        } else {
            unhandled(message);
        }
    }
}