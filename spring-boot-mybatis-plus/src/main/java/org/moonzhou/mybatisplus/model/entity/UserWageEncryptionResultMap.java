package org.moonzhou.mybatisplus.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 测试ResultMap方式，字段加密
 * @author moon zhou
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("user_wage_encryption_resultmap")
public class UserWageEncryptionResultMap {

    private Long id;

    private String money;

    private String remark;
}
