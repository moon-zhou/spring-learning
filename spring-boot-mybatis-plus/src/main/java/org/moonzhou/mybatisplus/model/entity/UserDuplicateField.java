package org.moonzhou.mybatisplus.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 通过TableName指定当前类对应的数据库表
 *
 * @author moon zhou
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user")
public class UserDuplicateField {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}