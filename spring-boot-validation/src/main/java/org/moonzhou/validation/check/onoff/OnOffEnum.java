package org.moonzhou.validation.check.onoff;

import lombok.Getter;

@Getter
public enum OnOffEnum implements EnumValidate<String> {

    ON("1", "open"),
    OFF("0", "close");

    private final String status;
    private final String desc;

    OnOffEnum(String status, String desc){
        this.status = status;
        this.desc = desc;
    }


    /**
     * 判断是否在枚举类当中
     * @param value
     * @return
     */
    @Override
    public boolean existValidate(String value) {
        if (value == null || "".equals(value)) {
            return false;
        }
        for (OnOffEnum testEnum : OnOffEnum.values()) {
            if (testEnum.getStatus().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

}
