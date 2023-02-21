package org.moonzhou.validation.check.idnum.validator;

import org.moonzhou.validation.check.idnum.annotation.IdNum;
import org.moonzhou.validation.check.idnum.util.IdNumUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
 
/**
 * 这个自定义注解逻辑处理类由于实现了ConstraintValidator接口，所以它默认被spring管理成bean,所以可以在这个逻辑处理类里面用@Autowiredu或者@Resources注入别的服务，而且不用在类上面用@Compent注解成spring的bean.
 * @ClassName IdNumValidator
 * @Description 身份证校验器
 * 通过实现ConstraintValidator，来实现自定义校验逻辑
 * @Author moon zhou
 **/
public class IdNumValidator implements ConstraintValidator<IdNum, String> {
 
    @Override
    public void initialize(IdNum myConstraint) {
        /* 初始化数据，如果有需要初始化的在此操作，没有则不需操作次函数 */
    }
 
    /**
     * @Description: 自定义校验逻辑
     * 逻辑代码，不符合返回false，否则返回true
     */
    @Override
    public boolean isValid(String idNum, ConstraintValidatorContext constraintValidatorContext) {
        return IdNumUtil.isValid(idNum);
    }
}