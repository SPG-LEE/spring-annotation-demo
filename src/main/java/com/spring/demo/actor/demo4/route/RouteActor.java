package com.spring.demo.actor.demo4.route;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;
import akka.routing.*;
import com.spring.demo.actor.demo4.request.AkkaEvenReq;
import com.spring.demo.actor.demo4.request.AkkaOddReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.spring.demo.extension.SpringExtension.SpringExtProvider;

@Component("routeActorDemo4")
@Scope("prototype")
public class RouteActor extends UntypedActor {

    private final ActorSystem actorSystem;
    private Router routerOdd;
    private Router routerEven;

    private void init() {
        List<Routee> oddRoutees = new ArrayList<>();
        List<Routee> evenRoutees = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            //借用上面的 inboxActor
            ActorRef worker = actorSystem.actorOf(SpringExtProvider.get(actorSystem)
                    .props("oddWorkerActor"), "oddWorkerActor_" + i);
            getContext().watch(worker);//监听
            oddRoutees.add(new ActorRefRoutee(worker));
        }
        /**
         * RoundRobinRoutingLogic: 轮询
         * BroadcastRoutingLogic: 广播
         * RandomRoutingLogic: 随机
         * SmallestMailboxRoutingLogic: 空闲
         */
        routerOdd = new Router(new RoundRobinRoutingLogic(), oddRoutees);
        for (int i = 0; i < 5; i++) {
            //借用上面的 inboxActor
            ActorRef worker = actorSystem.actorOf(SpringExtProvider.get(actorSystem)
                    .props("evenWorkerActor"), "evenWorkerActor_" + i);
            getContext().watch(worker);//监听
            evenRoutees.add(new ActorRefRoutee(worker));
        }
        /**
         * RoundRobinRoutingLogic: 轮询
         * BroadcastRoutingLogic: 广播
         * RandomRoutingLogic: 随机
         * SmallestMailboxRoutingLogic: 空闲
         */
        routerEven = new Router(new SmallestMailboxRoutingLogic(), evenRoutees);

    }

    @Autowired
    public RouteActor(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
        init();
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof AkkaOddReq) {
            System.out.println("oddRoute receive:" + message);
            routerOdd.route(message, getSender());
        } else if (message instanceof AkkaEvenReq) {
            System.out.println("evenRoute receive:" + message);
            routerEven.route(message, getSender());
        } else {
            System.out.println("route unhandled");
            unhandled(message);
        }
    }
}