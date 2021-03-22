/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: Common.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/3/20 17:21
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.xss.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CommonConstants {

    public static final List<String> ILLEGAL_CHAR = new ArrayList<>();

    static {
        ILLEGAL_CHAR.add("<");
        ILLEGAL_CHAR.add(">");
    }
}
