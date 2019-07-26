package com.spring.demo.actor.demo2.master;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;
import com.spring.demo.actor.demo2.request.AkkaListReq;
import com.spring.demo.actor.demo2.request.AkkaReq;
import com.spring.demo.actor.demo2.response.AkkaResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.spring.demo.extension.SpringExtension.SpringExtProvider;

@Component("masterActorDemo2")
@Scope("prototype")
public class MasterActor extends UntypedActor {

    private final ActorSystem actorSystem;

    @Autowired
    public MasterActor(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
    }


    @Override
    public void onReceive(Object message) {
        if (message instanceof AkkaListReq) {
            AkkaListReq message1 = (AkkaListReq) message;
            System.out.println("read req:" + message1.getData());
            while (message1.hasNext()){
                ActorRef worker = actorSystem.actorOf(SpringExtProvider.get(actorSystem).props("workerActorDemo2"));
                worker.tell(new AkkaReq(message1.read()), getSelf());
            }
        } else if (message instanceof AkkaResp) {
            AkkaResp respMessage = (AkkaResp) message;
            if (respMessage.isFinish()) {
                System.out.println("resp " + respMessage.getData() + " : " + respMessage.getStatus());
                unhandled(message);
            }
        }else {
            System.out.println("master unhandled");
            unhandled(message);
        }
    }
}