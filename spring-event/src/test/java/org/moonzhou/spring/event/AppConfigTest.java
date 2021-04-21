package org.moonzhou.spring.event;

import org.junit.Test;
import org.moonzhou.spring.event.event.MyEvent;
import org.moonzhou.spring.event.publisher.MyService;
import org.moonzhou.spring.event.publisher.RegistService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class AppConfigTest {

    /**
     * 直接运行，因为监听了ContextRefreshedEvent
     *
     * 测试结果：
     * bean刷新了...
     * name = org.springframework.context.annotation.internalConfigurationAnnotationProcessor
     * name = org.springframework.context.annotation.internalAutowiredAnnotationProcessor
     * name = org.springframework.context.annotation.internalCommonAnnotationProcessor
     * name = org.springframework.context.event.internalEventListenerProcessor
     * name = org.springframework.context.event.internalEventListenerFactory
     * name = appConfig
     * name = springContextRefreshEventListener
     */
    @Test
    public void testSpringEvent() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    /**
     * 测试自定义event-listener
     */
    @Test
    public void testMyEvent() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        MyEvent myEvent = new MyEvent("myEvent", 001L, "running~~~");

        // 通过ApplicationContext发布时间
        ctx.publishEvent(myEvent);
    }

    /**
     * 测试通过ApplicationEventPublisher发布自定义事件
     */
    @Test
    public void testApplicationEventPublisher() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        MyService myService = ctx.getBean(MyService.class);
        myService.doing();
    }

    /**
     * 测试<code>@EventListener</code>
     * 且多个监听（串行），可控制监听事件顺序
     */
    @Test
    public void testMultiEventListener() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        RegistService registService = ctx.getBean(RegistService.class);
        registService.regist();
    }

}