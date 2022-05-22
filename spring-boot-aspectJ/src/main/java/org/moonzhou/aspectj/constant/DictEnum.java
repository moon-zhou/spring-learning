package org.moonzhou.aspectj.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author moon zhou
 * @description 使用枚举模拟字典（持久化到db）
 * @email ayimin1989@163.com
 * @date 2022/4/24 14:37
 **/
@AllArgsConstructor
@Getter
public enum DictEnum {

    /**
     * 流程状态
     */
    PROCESS_STATUS_DRAFT(Common.DICT_ENUM_PROCESS_STATUS, "00", "草稿", "draft"),
    PROCESS_STATUS_SUBMITTED(Common.DICT_ENUM_PROCESS_STATUS, "01", "已提交，审核中", "submitted, under approval"),
    PROCESS_STATUS_MODIFY(Common.DICT_ENUM_PROCESS_STATUS, "02", "打回修改", "reject to modify"),
    PROCESS_STATUS_DECLINE(Common.DICT_ENUM_PROCESS_STATUS, "03", "拒绝", "decline"),
    PROCESS_STATUS_COMPLETED(Common.DICT_ENUM_PROCESS_STATUS, "04", "审批通过结束", "completed"),

    /**
     * 优先级
     */
    PRIORITY_LEVEL_LOW(Common.DICT_ENUM_PRIORITY_LEVEL, "01", "低", "low"),
    PRIORITY_LEVEL_MIDDLE(Common.DICT_ENUM_PRIORITY_LEVEL, "02", "中", "middle"),
    PRIORITY_LEVEL_HIGH(Common.DICT_ENUM_PRIORITY_LEVEL, "03", "高", "high"),

    /**
     * 员工级别
     */
    POSITION_LEVEL_GENERAL(Common.DICT_ENUM_POSITION_LEVEL, "001", "普通员工", "general staff"),
    POSITION_LEVE_MANAGER(Common.DICT_ENUM_POSITION_LEVEL, "002", "经理", "manager"),
    POSITION_LEVEL_DIRECTOR(Common.DICT_ENUM_POSITION_LEVEL, "003", "总监", "director"),
    POSITION_LEVEL_PRESIDENT(Common.DICT_ENUM_POSITION_LEVEL, "004", "总裁", "president"),
    POSITION_LEVEL_CHAIRMAN(Common.DICT_ENUM_POSITION_LEVEL, "005", "董事长", "chairman"),


    // last end char
    ;

    private String id;
    private String code;
    private String zhText;
    private String enText;

    public static String getTextByIdCode(String id, String code) {
        for (DictEnum dict : DictEnum.values()) {
            if (dict.getId().equals(id) && dict.getCode().equals(code)) {
                // 根据中英文，返回对应的值，此处默认中文
                return dict.getZhText();
            }
        }

        return null;
    }

    private static Map<String, DictEnum> dictEnumMap = new HashMap<>();
    private static final String DICT_ENUM_MAP_KEY_FORMAT = "%s-%s";
    static {
        for (DictEnum dict : DictEnum.values()) {
            dictEnumMap.put(String.format(DICT_ENUM_MAP_KEY_FORMAT, dict.getId(), dict.getCode()), dict);
        }
    }

    /**
     * 使用jvm缓存，采用hashmap，将时间复杂度从O(n)降到O(1)
     */
    public static String getTextByIdCodeEfficiently(String id, String code) {
        return dictEnumMap.get(String.format(DICT_ENUM_MAP_KEY_FORMAT, id, code)).getZhText();
    }
}
