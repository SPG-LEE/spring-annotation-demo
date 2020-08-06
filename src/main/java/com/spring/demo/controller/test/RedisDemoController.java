package com.spring.demo.controller.test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.InvalidActorNameException;
import com.spring.demo.actor.demo1.request.AkkaReq;
import com.spring.demo.actor.demo2.request.AkkaListReq;
import com.spring.demo.actor.demo4.request.AkkaOddReq;
import com.spring.demo.bean.AppResult;
import com.spring.demo.bean.AppResultBuilder;
import com.spring.demo.permission.AdminRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.spring.demo.extension.SpringExtension.SpringExtProvider;

@RestController
@RequestMapping("demo/redis")
@Validated
@CrossOrigin(allowedHeaders = {"x-access-token"})
public class RedisDemoController {
    @Autowired
    private AdminRedisService adminRedisService;
    @Autowired
    private ActorSystem actorSystem;

    @GetMapping("/create")
    public String createRedis() {
        return adminRedisService.createScheduleToken();
    }

    @PostMapping("/valid")
    public AppResult<String> validRedis(@RequestHeader("x-access-token") String token) {
        return adminRedisService.getAdmin(token);
    }

    @PostMapping("/valid/cycle1")
    public AppResult<List<String>> validRedisCycle1(@RequestHeader("x-access-token") String token) {
        List<Integer> iterator = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            iterator.add(i);
        }
        List<String> result = new ArrayList<>();
        iterator.parallelStream().forEach(i -> {
            AppResult<String> valid = adminRedisService.getAdmin(token);
            if (!valid.isSuccess()) {
                result.add(valid.getCode());
            }
        });
        return AppResultBuilder.buildSuccessResult(result, "0000");
    }

    @PostMapping("demo3")
    public void actorDemo3(@RequestHeader("x-access-token") String token) {

//        List<String> data = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            data.add(token);
//        }
//        com.spring.demo.actor.demo3.request.AkkaListReq akkaListReq = new com.spring.demo.actor.demo3.request.AkkaListReq(data);
//        try {
//            ActorRef master = actorSystem.actorOf(SpringExtProvider.get(actorSystem).props("masterActorDemo3"), "masterActorDemo3");
//            master.tell(akkaListReq, master);
//        } catch (InvalidActorNameException e) {
//            actorSystem.actorSelection("*/masterActorDemo3").tell(akkaListReq, ActorRef.noSender());
//        }
    }
}
