package org.moonzhou.mybatisplus.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 测试注解方式，字段加密
 * @author moon zhou
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("user_wage_encryption_annotation")
public class UserWageEncryptionAnnotation {

    @TableField("id")
    private Long id;

    @TableField("money")
    private String money;

    @TableField("remark")
    private String remark;
}
