package org.moonzhou.transaction.mybatisplus.handler;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author moon zhou
 */
@Component
// @ConditionalOnMissingBean(MetaObjectHandler.class)
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        // 可以根据接口动态获取当前登录人
        this.strictInsertFill(metaObject, "createUser", String.class, "moon-001");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        // 可以根据接口动态获取当前登录人
        this.strictUpdateFill(metaObject, "updateUser", String.class, "moon-002");
    }
}
