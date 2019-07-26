package com.spring.demo.extension;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;

public class ActorFactoryProducer implements IndirectActorProducer {

    final private ApplicationContext applicationContext;
    final private String actorBeanName;

    public ActorFactoryProducer(ApplicationContext applicationContext, String actorBeanName) {
        this.applicationContext = applicationContext;
        this.actorBeanName = actorBeanName;
    }

    @Override
    public Actor produce() {
        return (Actor) applicationContext.getBean(actorBeanName);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
    }
    public Props create(String beanName) {
        return Props.create(SpringExtension.SpringExt.class, this.applicationContext, beanName);
    }

}