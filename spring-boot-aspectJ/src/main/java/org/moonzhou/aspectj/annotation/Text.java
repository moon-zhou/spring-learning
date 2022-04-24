package org.moonzhou.aspectj.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Text {

    /**
     * TODO 待实现
     * description: return field name, default value is annotation field append 'Text'
     * @return
     */
    String field() default "";

    /**
     * 配置枚举：code->zh-text->en-text
     * 可类比为字典配置
     * 此值配置之后，value可不配置，如果都配置
     */
    String enumCode();

    /**
     * TODO 待实现
     * description: return field value, default empty
     */
    String value() default "";
}