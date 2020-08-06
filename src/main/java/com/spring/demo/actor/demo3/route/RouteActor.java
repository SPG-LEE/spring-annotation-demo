package com.spring.demo.actor.demo3.route;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;
import akka.routing.*;
import com.spring.demo.actor.demo3.request.AkkaReq;
import com.spring.demo.actor.demo4.request.AkkaEvenReq;
import com.spring.demo.actor.demo4.request.AkkaOddReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.spring.demo.extension.SpringExtension.SpringExtProvider;

@Component("routeActorDemo3")
@Scope("prototype")
public class RouteActor extends UntypedActor {

    private final ActorSystem actorSystem;
    private Router router;

    private void init() {
        List<Routee> routees = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //借用上面的 inboxActor
            ActorRef worker = actorSystem.actorOf(SpringExtProvider.get(actorSystem)
                    .props("workerActorDemo3"), "workerActorDemo3_" + i);
            getContext().watch(worker);//监听
            routees.add(new ActorRefRoutee(worker));
        }
        /**
         * RoundRobinRoutingLogic: 轮询
         * BroadcastRoutingLogic: 广播
         * RandomRoutingLogic: 随机
         * SmallestMailboxRoutingLogic: 空闲
         */
        router = new Router(new RoundRobinRoutingLogic(), routees);


    }

    @Autowired
    public RouteActor(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
        init();
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof AkkaReq) {
            router.route(message, getSender());
        } else {
            System.out.println("route unhandled");
            unhandled(message);
        }
    }
}