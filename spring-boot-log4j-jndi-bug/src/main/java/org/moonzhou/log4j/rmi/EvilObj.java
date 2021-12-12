/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: EvilObj.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/12/12 16:26
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.log4j.rmi;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class EvilObj {
    static {
        System.out.println("恶意执行!直接网站漏洞执行！");
        // ...恶意代码块
    }
}
