package org.moonzhou.mybatisplus.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

public abstract class BaseEntity extends BaseEntityLite {
    @TableId(
        type = IdType.ASSIGN_ID
    )
    private Long id;
    @TableLogic
    private boolean deleted;

    public BaseEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
