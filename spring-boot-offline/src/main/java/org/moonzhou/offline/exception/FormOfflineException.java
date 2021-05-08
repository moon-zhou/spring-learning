/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: FormOfflineException.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/5/8 11:32
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.offline.exception;

/**
 * 功能描述: 表单提交/访问页面请求下线抛出的异常<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class FormOfflineException extends OfflineException {
    public FormOfflineException() {
    }

    public FormOfflineException(String message) {
        super(message);
    }

    public FormOfflineException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormOfflineException(Throwable cause) {
        super(cause);
    }
}
