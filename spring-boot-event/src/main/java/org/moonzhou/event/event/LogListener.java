package org.moonzhou.event.event;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.event.utils.JsonUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author moon zhou
 * @version 1.0
 * @description: log listener
 * @date 2022/10/10 17:33
 */
@Slf4j
@Component
public class LogListener {

    @EventListener
    @Async("asyncExecutor")
    public void asyncLog(LogEvent logEvent) {
        log.info("async log...");
        log.info(JsonUtils.toJson(logEvent.getLogParam()));
    }

    @EventListener
    public void syncLog(LogEvent logEvent) {
        log.info("sync log...");
        log.info(JsonUtils.toJson(logEvent.getLogParam()));
    }
}
