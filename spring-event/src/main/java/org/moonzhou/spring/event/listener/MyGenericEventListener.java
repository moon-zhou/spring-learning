package org.moonzhou.spring.event.listener;

import lombok.extern.java.Log;
import org.moonzhou.spring.event.dto.User;
import org.moonzhou.spring.event.event.MyEvent;
import org.moonzhou.spring.event.event.MyGenericEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Log
//public class MyGenericEventListener implements ApplicationListener<MyGenericEvent<User>> {
public class MyGenericEventListener {

    /*@Override
    public void onApplicationEvent(MyGenericEvent<User> event) {

    }*/

    /**
     * 可根据事件泛型参数，定义对应的监听做对应业务处理。
     * 如果出现过多的监听，整体结构都会显得重复，因而出现了<code>ResolvableTypeProvider</code>
     * @param event
     */
    @EventListener
    public void listen(MyGenericEvent<User> event) {
        User genericParameter = event.getGenericParameter();
        log.info("generic param is: " + genericParameter.toString());
    }
}