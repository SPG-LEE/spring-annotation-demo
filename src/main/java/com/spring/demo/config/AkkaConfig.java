package com.spring.demo.config;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.FromConfig;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.spring.demo.extension.SpringExtension.SpringExtProvider;

@Configuration
public class AkkaConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create("ActorSystem");
        SpringExtProvider.get(actorSystem).initialize(applicationContext);
        return actorSystem;
    }
    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }
}