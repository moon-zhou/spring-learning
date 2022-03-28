package org.moonzhou.mybatisplus.model.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 默认情况下，如果数据库表是使用标准的下划线命名，并且能对应上实体类的类名，我们就不需要特别去手动匹配。比如有张 user_info 表，那么会自动匹配UserInfo这个实体类
 *
 * 同表名一样，如果数据库表里的字段名使用标准的下划线命名，并且能对应上实体类的成员名称（驼峰命名），我们就不需要特别去手动匹配，比如createTime字段对应数据库里create_time
 *
 * @author moon zhou
 */
@Data
public class UserInfo {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private LocalDateTime createTime;
}