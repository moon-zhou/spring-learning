package org.moonzhou.spring.event.listener;

import org.moonzhou.spring.event.event.MyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("监听到事件: " + event.getId() + "	" + event.getMessage());
    }
}