package org.moonzhou.event.service;

import org.moonzhou.event.param.LogParam;

/**
 * @author: moon zhou
 * @date 2022/10/12 11:13
 * @version 1.0
 */
public interface LogService {
    void async(LogParam logParam);
}
