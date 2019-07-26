package com.spring.demo.controller.test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.InvalidActorNameException;
import com.spring.demo.actor.demo1.request.AkkaReq;
import com.spring.demo.actor.demo2.request.AkkaListReq;
import com.spring.demo.actor.demo4.request.AkkaOddReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.spring.demo.extension.SpringExtension.SpringExtProvider;

@RestController
@RequestMapping("demo/akka")
@Validated
@CrossOrigin(allowedHeaders = {"x-access-token"})
public class AkkaDemoController {

    @Autowired
    ActorSystem actorSystem;

    @GetMapping("demo1")
    public void actorDemo1() {
        try {
            ActorRef master = actorSystem.actorOf(SpringExtProvider.get(actorSystem).props("masterActorDemo1"), "masterActorDemo1");
            System.out.println(master.path());
            master.tell(new AkkaReq("123a"), master);
        } catch (InvalidActorNameException e) {
            actorSystem.actorSelection("*/masterActorDemo1").tell(new AkkaReq("123a"), ActorRef.noSender());
        }
    }

    @GetMapping("demo2")
    public void actorDemo2() {
        ActorRef master = actorSystem.actorOf(SpringExtProvider.get(actorSystem).props("masterActorDemo2"), "masterActorDemo2");
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("abc" + i);
        }
        AkkaListReq akkaListReq = new AkkaListReq(data);
        master.tell(akkaListReq, master);
    }

    @GetMapping("demo3")
    public void actorDemo3() {

        ActorRef master = actorSystem.actorOf(SpringExtProvider.get(actorSystem).props("masterActorDemo3"), "masterActorDemo3");
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("abc" + i);
        }
        com.spring.demo.actor.demo3.request.AkkaListReq akkaListReq = new com.spring.demo.actor.demo3.request.AkkaListReq(data);
        master.tell(akkaListReq, master);
    }

    @GetMapping("demo4")
    public void actorDemo4() {

        List<AkkaOddReq> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add(new AkkaOddReq("abc" + i));
        }

        com.spring.demo.actor.demo4.request.AkkaListReq akkaListReq = new com.spring.demo.actor.demo4.request.AkkaListReq(data);
        try {
            ActorRef master = actorSystem.actorOf(SpringExtProvider.get(actorSystem).props("masterActorDemo4"), "masterActorDemo4");
            master.tell(akkaListReq, ActorRef.noSender());
        } catch (InvalidActorNameException e) {
            actorSystem.actorSelection("*/masterActorDemo4").tell(akkaListReq, ActorRef.noSender());
        }
    }


}
