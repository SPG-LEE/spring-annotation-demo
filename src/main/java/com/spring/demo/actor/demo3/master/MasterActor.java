package com.spring.demo.actor.demo3.master;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.spring.demo.actor.demo3.request.AkkaListReq;
import com.spring.demo.actor.demo3.request.AkkaReq;
import com.spring.demo.actor.demo3.response.AkkaResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.spring.demo.extension.SpringExtension.SpringExtProvider;

@Component("masterActorDemo3")
@Scope("prototype")
public class MasterActor extends UntypedActor {

    private final ActorSystem actorSystem;

    @Autowired
    public MasterActor(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
    }

    private ActorRef route;
    @Override
    public void preStart() {
        route = createActors();
    }
    @Override
    public void onReceive(Object message) {
        if (message instanceof AkkaListReq) {
            AkkaListReq message1 = (AkkaListReq) message;
            System.out.println("read req:" + message1.getData());

            AkkaListReq msg = (AkkaListReq) message;
            do {
                route.tell(new AkkaReq(msg.readData(), msg.readStudent()), self());
            } while (msg.hasNext());
        } else if (message instanceof AkkaResp) {
            AkkaResp respMessage = (AkkaResp) message;
            if (respMessage.isFinish()) {
                System.out.println("resp " + respMessage.getData() + " : " + respMessage.getStatus());
                unhandled(message);
            }
        } else {
            System.out.println("master unhandled");
            unhandled(message);
        }
    }

    private ActorRef createActors() {
        Props props = SpringExtProvider.get(actorSystem)
                .props("routeActorDemo3");
        return actorSystem.actorOf(props, "routeActorDemo3");
    }
}