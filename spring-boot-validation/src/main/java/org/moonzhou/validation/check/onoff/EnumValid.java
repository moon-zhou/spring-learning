package org.moonzhou.validation.check.onoff;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 用于对枚举类校验的自定义注解
 *
 * @author moon zhou
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EnumValidator.class})
@Documented
public @interface EnumValid {


    String message() default "";


    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};


    Class<?>[] target() default {};

    /**
     * 允许的枚举
     *
     * @return
     */
    Class<? extends Enum<?>> enumClass();
}
