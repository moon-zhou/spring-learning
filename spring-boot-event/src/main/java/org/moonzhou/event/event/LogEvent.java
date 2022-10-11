package org.moonzhou.event.event;

import lombok.Getter;
import org.moonzhou.event.param.LogParam;
import org.springframework.context.ApplicationEvent;

/**
 * @author moon zhou
 * @version 1.0
 * @description: log event
 * @date 2022/10/10 17:23
 */
public class LogEvent extends ApplicationEvent {

    @Getter
    private final LogParam logParam;

    public LogEvent(LogParam logParam) {
        super(logParam);
        this.logParam = logParam;
    }
}
