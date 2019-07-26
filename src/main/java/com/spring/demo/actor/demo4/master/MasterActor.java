package com.spring.demo.actor.demo4.master;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.spring.demo.actor.demo4.request.AkkaEvenReq;
import com.spring.demo.actor.demo4.request.AkkaListReq;
import com.spring.demo.actor.demo4.request.AkkaOddReq;
import com.spring.demo.actor.demo4.response.AkkaResp;
import com.spring.demo.exception.ReadAkkaReqException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.spring.demo.extension.SpringExtension.SpringExtProvider;

@Component("masterActorDemo4")
@Scope("prototype")
public class MasterActor extends UntypedActor {

    private final ActorSystem actorSystem;

    private ActorRef route;
    private AkkaListReq req;

    @Autowired
    public MasterActor(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
    }

    @Override
    public void preStart() {
        route = createActors();
    }

    @Override
    public void onReceive(Object message) throws ReadAkkaReqException {
        if (message instanceof AkkaListReq) {
            AkkaListReq message1 = (AkkaListReq) message;
            System.out.println("read req:" + message1.getData());
            req = message1;
            System.out.println("req-" + req.hashCode() + ":" + req.getData());
            int i = 0;
            while (((AkkaListReq) message).hasNext()) {
                i++;
                switch (i % 2) {
                    case 0:
                        route.tell(new AkkaEvenReq(((AkkaListReq) message).read()), self());
                        continue;
                    case 1:
                        route.tell(new AkkaOddReq(((AkkaListReq) message).read()), self());
                        continue;
                    default:
                        System.out.println("error");
                }
            }
        } else if (message instanceof AkkaResp) {
            AkkaResp respMessage = (AkkaResp) message;
            if (respMessage.isFinish()) {
                System.out.println("resp " + respMessage.getData() + " : " + respMessage.getStatus());
                req.finish(respMessage);
            }
        } else {
            System.out.println("master unhandled");
            unhandled(message);
        }
    }

    private ActorRef createActors() {
        Props props = SpringExtProvider.get(actorSystem)
                .props("routeActorDemo4");
        return actorSystem.actorOf(props, "routeActorDemo4");
    }
}