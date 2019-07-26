package com.spring.demo.actor.demo4.worker;

import akka.actor.UntypedActor;
import com.spring.demo.actor.demo4.request.AkkaOddReq;
import com.spring.demo.actor.demo4.response.AkkaResp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("oddWorkerActor")
@Scope("prototype")
public class OddWorkerActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof AkkaOddReq) {
            System.out.println("tread:" + Thread.currentThread() + ">>" + this.self().path() + "oddWorker receive req:" + ((AkkaOddReq) message).getData());
            Thread.sleep(2000);
            AkkaResp akkaResp = new AkkaResp(((AkkaOddReq) message).getData());
            akkaResp.finish();
            getSender().tell(akkaResp, getSelf());
            //getContext().stop(getSelf());
        } else {
            System.out.println("oddWorker unhandled");
            unhandled(message);
        }
    }
}