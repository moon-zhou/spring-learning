package org.moonzhou.event;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author moon zhou
 * @version 1.0
 * @description: log param
 * @date 2022/10/10 17:25
 */
@Accessors(chain = true)
@Data
public class LogParam {
    private String requestId;
    private String param;
    private String operateTime;
}
