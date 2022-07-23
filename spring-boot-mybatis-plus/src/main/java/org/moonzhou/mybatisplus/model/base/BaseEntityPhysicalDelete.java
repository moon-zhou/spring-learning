package org.moonzhou.mybatisplus.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

/**
 * physical delete
 * @author moon zhou
 */
@Getter
@Setter
public abstract class BaseEntityPhysicalDelete extends BaseEntityLite {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
}