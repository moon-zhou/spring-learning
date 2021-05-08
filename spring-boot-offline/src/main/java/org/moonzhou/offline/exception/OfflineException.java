/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: OfflineException.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/5/8 11:27
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.offline.exception;

/**
 * 功能描述: 运行时基类异常<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class OfflineException extends RuntimeException {

    private String extraDetailMessage;

    public OfflineException() {
        super();
    }

    public OfflineException(String message) {
        super(message);
    }

    public OfflineException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfflineException(Throwable cause) {
        super(cause);
    }

    public void setExtraDetailMessage(String extraDetailMessage) {
        this.extraDetailMessage = extraDetailMessage;
    }

    @Override
    public String getMessage() {
        if (extraDetailMessage == null) {
            return super.getMessage();
        }
        if (super.getMessage() != null) {
            return super.getMessage() + " " + extraDetailMessage;
        } else {
            return extraDetailMessage;
        }
    }
}
