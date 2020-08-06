package com.spring.demo.actor.demo3.worker;

import akka.actor.UntypedActor;
import com.spring.demo.actor.demo3.request.AkkaReq;
import com.spring.demo.actor.demo3.response.AkkaResp;
import com.spring.demo.bean.AppResult;
import com.spring.demo.entity.Class;
import com.spring.demo.entity.Student;
import com.spring.demo.permission.AdminRedisService;
import com.spring.demo.service.ClassService;
import com.spring.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("workerActorDemo3")
@Scope("prototype")
public class WorkerActor extends UntypedActor {
    @Autowired
    private ClassService classService;

    @Override
    public void onReceive(Object message) {
        if (message instanceof AkkaReq) {
//            System.out.println("tread:" + Thread.currentThread() + ">>" + this.self().path() + " receive req:" + ((AkkaReq) message).getData());
            AkkaReq msg = (AkkaReq) message;

            Student student = msg.getStudent();
//            Class classDb = classService.findById(student.getClasses().getId());
//            classDb.setMark(classDb.getMark() + 1);
//            classService.save(classDb);
            classService.addMark(student.getClasses().getId(),1);
//            student.getClasses().setName("22222");
            //getContext().stop(getSelf());
        } else {
            System.out.println("worker unhandled");
            unhandled(message);
        }
    }
}