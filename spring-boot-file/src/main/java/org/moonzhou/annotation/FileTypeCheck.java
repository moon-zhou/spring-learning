package org.moonzhou.annotation;


import org.moonzhou.validator.FileTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD})  // 作用于字段，枚举上
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileTypeValidator.class}) // 校验的逻辑处理类
@Documented
public @interface FileTypeCheck {

    /**
     * 黑名单/白名单
     * @return
     */
    Mode mode() default Mode.WHITE_LIST;

    /**
     * 白名单：列举出来的均为支持的文件类型，其余的不支持
     * @return
     */
    String[] include() default {};

    /**
     * 黑名单：列举出来的均为不支持的类型，其余的均支持
     * @return
     */
    String[] exclude() default {};

    /**
     * 提示的信息
     * @return
     */
    String message() default "文件类型不符合要求";
 
    Class<?>[] groups() default { };  // 分组验证，例如只在新增时进行校验等
 
    Class<? extends Payload>[] payload() default { };

    enum Mode {
        WHITE_LIST,
        BLACK_LIST,
        ;
    }
}