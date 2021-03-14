/*
 * Copyright (C), 2002-2020, moon-zhou
 * FileName: ResponseController.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2020/10/10 10:29
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.response.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: 测试返回数据类型<br>
 *     涉及跳转页面，不能使用RestController注解
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("responseTest")
public class ResponseController {

    /**
     * http://localhost:8080/responseTest/multiResponseType
     *
     * http://localhost:8080/responseTest/multiResponseType?type=json
     *
     * http://localhost:8080/responseTest/multiResponseType?type=page
     * 既可以跳转页面，也可以返回json数据
     * @param type
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/multiResponseType")
    public Object multiResponseType(String type, HttpServletResponse response) throws IOException {
        if ("page".equals(type)) {
            // 返回动态页面
            ModelAndView user = new ModelAndView("user");
            user.addObject("name", "hou");
            return user;
        } else if ("json".equals(type)) {

            // 返回json格式数据
            ObjectMapper objectMapper = new ObjectMapper();
            HashMap<String, String> msg = new HashMap<>();
            msg.put("msg", "data");
            msg.put("type", type);

            response.setCharacterEncoding ("UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(msg));
            return null;
        } else {
            // 返回静态页面
            return "error";
        }
    }

    /**
     * http://localhost:8080/hello.html
     * 直接访问到static目录下的页面
     *
     * http://localhost:8080/responseTest/toStaticPage
     * 访问到的是templates目录下的页面
     *
     * 跳转静态页面，一定不能有@ResponseBody和@RestController
     *
     * 参考：https://www.cnblogs.com/guo-xu/p/11203740.html
     * @return
     */
    @RequestMapping("/toStaticPage")
    public String toStaticPage() {
        // 返回对应的名为success的页面
        return "hello";
    }

    /**
     * 跳转动态页面，并且返回值
     *
     * http://localhost:8080/responseTest/toDynamicPage
     *
     * 参考：https://blog.csdn.net/ZhengJCheng/article/details/96984114
     * @return
     */
    @RequestMapping("/toDynamicPage")
    public ModelAndView toUser() {

        ModelAndView user = new ModelAndView("user");
        user.addObject("name", "moon");

        return user;
    }

    /**
     * http://localhost:8080/responseTest/getJsonData
     * 返回json数据
     * @return
     */
    @RequestMapping("/getJsonData")
    @ResponseBody
    public List<Map<String, Object>> getJsonData() { //查找所有部门

        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "a1");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "a2");

        result.add(map1);
        result.add(map2);

        return result;
    }


}
