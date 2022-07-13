package org.moonzhou.mybatisplus.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.moonzhou.mybatisplus.model.base.BaseEntity;

/**
 * 困难描述
 * 测试TableField，必须使用lambda方式，或者自定义sql时使用别名或者resultMap指定字段
 * @author moonzhou
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("hardship_aid_apply")
public class HardShipAidApply extends BaseEntity {

    private String name;

    /**
     * 驼峰默认匹配下划线id_no
     */
    private String idNo;

    private int age;

    /**
     * 驼峰默认匹配下划线difficult_description
     */
    // private String difficultDescription;

    /**
     * mybatis-plus annotation TableField cannot work, so we can not use self xml sql, lambda query instead
     */
    @TableField("difficult_description")
    private String description;

    private String userNo;

    private String dept;
}
