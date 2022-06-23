package org.moonzhou.mybatisplus.model.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.time.LocalDateTime;

public abstract class BaseEntityLite {
    @TableField(
        fill = FieldFill.INSERT,
        value = "create_time"
    )
    private LocalDateTime createTime;
    @TableField(
        fill = FieldFill.INSERT,
        value = "create_user"
    )
    private String createUser;
    @TableField(
        fill = FieldFill.UPDATE,
        value = "update_time"
    )
    private LocalDateTime updateTime;
    @TableField(
        fill = FieldFill.UPDATE,
        value = "update_user"
    )
    private String updateUser;

    public BaseEntityLite() {
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public LocalDateTime getUpdateTime() {
        return this.updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
