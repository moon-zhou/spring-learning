package org.moonzhou.mybatisplus.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseEntity extends BaseEntityLite {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableLogic
    private boolean deleted;

}
