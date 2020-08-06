package com.spring.demo.actor.demo4.worker;

import akka.actor.UntypedActor;
import com.spring.demo.actor.demo4.request.AkkaEvenReq;
import com.spring.demo.actor.demo4.request.AkkaOddReq;
import com.spring.demo.actor.demo4.response.AkkaResp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("evenWorkerActor")
@Scope("prototype")
public class EvenWorkerActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof AkkaEvenReq) {
            AkkaEvenReq msg = (AkkaEvenReq) message;
            try {
                if (msg.getData().equals("abc1")){
                    throw  new Exception();
                }
            }catch (Exception e){
                ((AkkaEvenReq) message).setData("error");
                getSelf().tell(message,getSender());
                return;
            }
            System.out.println("tread:" + Thread.currentThread() + ">>" + this.self().path() + "evenWorker receive req:" + msg.getData());
            Thread.sleep(2000);
            AkkaResp akkaResp = new AkkaResp(msg.getData());
            akkaResp.finish();
            getSender().tell(akkaResp, getSelf());
            //getContext().stop(getSelf());
        } else {
            System.out.println("evenWorker unhandled");
            unhandled(message);
        }
    }
}