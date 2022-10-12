package org.moonzhou.event.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author moon zhou
 * @version 1.0
 * @description: after wards， async
 * @date 2022/10/11 17:20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AsyncMethod {

    Class<?>[] value();

    Type type();

}
