package org.moonzhou.event.service.impl;

import org.moonzhou.event.annotation.AsyncMethod;
import org.moonzhou.event.annotation.Type;
import org.moonzhou.event.event.LogEvent;
import org.moonzhou.event.param.LogParam;
import org.moonzhou.event.service.LogService;
import org.springframework.stereotype.Service;

/**
 * @author moon zhou
 * @date 2022/10/12 11:14
 * @version 1.0
 */
@Service
public class LogServiceImpl implements LogService {

    @AsyncMethod(value = {LogEvent.class}, type = Type.AFTER)
    @Override
    public void async(LogParam logParam) {

    }
}
