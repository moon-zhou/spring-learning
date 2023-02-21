package org.moonzhou.validation.check.onoff;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 枚举校验器
 * 用于校验EnumValidator
 *
 * @author moon zhou
 */
public class EnumValidator implements ConstraintValidator<EnumValid, String> {

    private Class<? extends Enum> enumClass;

    @Override
    public void initialize(EnumValid enumValid) {
        enumClass = enumValid.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || "".equals(value)) {
            return true;
        }
        EnumValidate[] enums = (EnumValidate[]) enumClass.getEnumConstants();
        if (enums == null || enums.length == 0) {
            return false;
        }
        return enums[0].existValidate(value);
    }

}
