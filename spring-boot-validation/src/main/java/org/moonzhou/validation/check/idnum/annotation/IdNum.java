package org.moonzhou.validation.check.idnum.annotation;


import org.moonzhou.validation.check.idnum.validator.IdNumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
@Target({ ElementType.FIELD})  // 作用于字段，枚举上
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdNumValidator.class) // 校验的逻辑处理类
public @interface IdNum {
 
    String message() default "身份证号码不正确";   // 提示的信息
 
    Class<?>[] groups() default { };  // 分组验证，例如只在新增时进行校验等
 
    Class<? extends Payload>[] payload() default { };
}