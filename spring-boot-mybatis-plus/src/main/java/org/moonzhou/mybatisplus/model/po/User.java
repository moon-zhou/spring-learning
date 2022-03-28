package org.moonzhou.mybatisplus.model.po;

import lombok.Data;

/**
 * 默认情况下，如果数据库表是使用标准的下划线命名，并且能对应上实体类的类名，我们就不需要特别去手动匹配。比如有张 user_info 表，那么会自动匹配UserInfo这个实体类
 *
 * @author moon zhou
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}