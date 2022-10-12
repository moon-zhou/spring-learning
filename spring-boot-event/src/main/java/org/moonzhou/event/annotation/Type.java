package org.moonzhou.event.annotation;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author moon zhou
 * @version 1.0
 * @date 2022/10/12 11:29
 */
@Getter
@AllArgsConstructor
public enum Type {

    BEFORE("before"),
    AFTER("after"),

    ;

    private String value;
}
