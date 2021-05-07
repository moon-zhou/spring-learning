/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: Offline.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/5/6 17:28
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.offline.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述:Offline annotation which method used means the http service has been offline.<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Offline {

    /**
     * 下线注解，默认下线
     * @return
     */
    boolean offline() default true;
}
