package com.test.eurekaserver;

import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEvent {
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event){
        System.out.println(event.getServerId()+"============下线了");
//        可发送邮件
    }
}
