package com.spring.demo.actor.demo1.master;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;
import akka.routing.Router;
import com.spring.demo.actor.demo1.request.AkkaReq;
import com.spring.demo.actor.demo1.response.AkkaResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.spring.demo.extension.SpringExtension.SpringExtProvider;

@Component("masterActorDemo1")
@Scope("prototype")
public class MasterActor extends UntypedActor {

    private final ActorSystem actorSystem;
    private Router router;
    private static final int WORKER_COUNT = 5;

    @Autowired
    public MasterActor(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
    }


    @Override
    public void onReceive(Object message) {
        ActorRef worker = actorSystem.actorOf(SpringExtProvider.get(actorSystem).props("workerActorDemo1"));
        if (message instanceof AkkaReq) {
            AkkaReq message1 = (AkkaReq) message;
            worker.tell(message, getSelf());
            System.out.println("get resp:" + message1.getData());
        }else if(message instanceof AkkaResp){
            AkkaResp respMessage = (AkkaResp) message;
            System.out.println("resp " + respMessage.getData() + " : " + respMessage.getStatus());
//            actorSystem.stop(getSelf());
        }else {
            unhandled(message);
//            actorSystem.stop(getSelf());
        }
    }
}