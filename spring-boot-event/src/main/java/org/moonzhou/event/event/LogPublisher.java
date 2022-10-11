package org.moonzhou.event.event;

import org.moonzhou.event.param.LogParam;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author moon zhou
 * @version 1.0
 * @description: log publisher
 * @date 2022/10/10 17:54
 */
@Component
public class LogPublisher implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void sendEvent(LogParam logParam) {
        LogEvent logEvent = new LogEvent(logParam);
        applicationEventPublisher.publishEvent(logEvent);
    }
}
