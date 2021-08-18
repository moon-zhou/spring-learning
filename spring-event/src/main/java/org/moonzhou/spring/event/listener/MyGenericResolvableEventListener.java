package org.moonzhou.spring.event.listener;

import lombok.extern.java.Log;
import org.moonzhou.spring.event.dto.User;
import org.moonzhou.spring.event.event.MyGenericEvent;
import org.moonzhou.spring.event.event.MyGenericResolvableEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;
import org.springframework.stereotype.Component;

@Component
@Log
public class MyGenericResolvableEventListener {

    @EventListener
    public void listen(MyGenericResolvableEvent<?> event) {
        ResolvableType resolvableType = event.getResolvableType();

    }
}