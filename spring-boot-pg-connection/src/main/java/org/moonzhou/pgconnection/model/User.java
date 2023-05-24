package org.moonzhou.pgconnection.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 默认情况下，如果数据库表是使用标准的下划线命名，并且能对应上实体类的类名，我们就不需要特别去手动匹配。比如有张 user_info 表，那么会自动匹配UserInfo这个实体类
 *
 * @author moon zhou
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_user")
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private LocalDateTime createTime;
}