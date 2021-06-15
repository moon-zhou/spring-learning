/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: CustomizedController.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/6/10 17:25
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.profile.web;

import org.moonzhou.profile.config.bean.Duts;
import org.moonzhou.profile.config.bean.Security;
import org.moonzhou.profile.config.bean.SystemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("/customized/config")
public class CustomizedController {

    @Autowired
    private Security security;

    @Autowired
    private SystemInfo systemInfo;

    @Value("${system-info.name}")
    private String systemName;

    @Value("${security.system}")
    private String securitySystem;

    @Autowired
    private Duts duts;

    /**
     * 测试返回json数据：配置到组件扩展的文件里application-xxx.yml，通过绑定POJO使用
     * http://localhost:8083/customized/config/security
     * @return
     */
    @RequestMapping("security")
    @ResponseBody
    Map<String, Object> security() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", security.toString());

        return result;
    }

    /**
     * 测试返回json数据：配置到组件配置文件application.yml，通过绑定POJO使用
     * http://localhost:8083/customized/config/system
     * @return
     */
    @RequestMapping("system")
    @ResponseBody
    Map<String, Object> system() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", systemInfo.toString());

        return result;
    }

    /**
     * 测试返回json数据：配置到组件配置文件application.yml，通过注解使用
     * http://localhost:8083/customized/config/value
     * @return
     */
    @RequestMapping("value")
    @ResponseBody
    Map<String, Object> value() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data1", systemName);
        result.put("data2", securitySystem);

        return result;
    }

    /**
     * 测试返回json数据：单独配置文件，duts，通过绑定POJO使用
     * http://localhost:8083/customized/config/single
     * @return
     */
    @RequestMapping("single")
    @ResponseBody
    Map<String, Object> single() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", duts.toString());

        return result;
    }
}
