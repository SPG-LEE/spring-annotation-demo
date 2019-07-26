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


    @Override
    public void onReceive(Object message) {
        if (message instanceof AkkaListReq) {
            AkkaListReq message1 = (AkkaListReq) message;
            System.out.println("read req:" + message1.getData());

            List<ActorRef> actors = createActors(((AkkaListReq) message).getData().size());
            actors.stream().parallel().forEach(actorRef -> {
                actorRef.tell(new AkkaReq(((AkkaListReq) message).read()),self());
            });
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
    private List<ActorRef> createActors(int actorCount) {
        Props props = SpringExtProvider.get(actorSystem)
                .props("workerActorDemo3")
                .withDispatcher("my-dispatcher");

        List<ActorRef> actors = new ArrayList<>(actorCount);
        for (int i = 0; i < actorCount; i++) {
            actors.add(actorSystem.actorOf(props,"workerActorDemo3_"+ i));
        }
        return actors;
    }
}