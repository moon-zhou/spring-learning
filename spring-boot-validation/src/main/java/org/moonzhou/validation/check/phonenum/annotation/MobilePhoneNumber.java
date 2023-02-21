package org.moonzhou.validation.check.phonenum.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@Pattern(regexp = "^1[\\d]{10}")
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@ReportAsSingleViolation
public @interface MobilePhoneNumber {
 
    String message() default "请输入11位手机号";
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
 
}