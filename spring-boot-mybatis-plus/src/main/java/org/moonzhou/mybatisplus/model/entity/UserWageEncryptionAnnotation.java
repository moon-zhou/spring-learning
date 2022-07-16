package org.moonzhou.mybatisplus.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.moonzhou.mybatisplus.model.handler.EncryptionHandler;

/**
 * 测试注解方式，字段加密
 * @author moon zhou
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "user_wage_encryption_annotation", autoResultMap = true)
public class UserWageEncryptionAnnotation {

    @TableField("id")
    private Long id;

    @TableField(value = "money", typeHandler = EncryptionHandler.class)
    private String money;

    @TableField(value = "remark", typeHandler = EncryptionHandler.class)
    private String remark;
}
