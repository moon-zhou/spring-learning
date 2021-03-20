package org.moonzhou.xss.web;

import org.moonzhou.xss.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2021/3/16 17:35
 * @since 1.0
 */
@Controller
@RequestMapping("/xss")
public class XSSController {

    /**
     * 简单的XSS测试示例，弹框
     */
    private static final String XSS_SCRIPT_ALERT = "<script>alert('it is a xss test!')</script>";

    /**
     * http://localhost:8080/xss/simpleDemo
     * rest接口，返回的数据直接在页面上展示
     *
     * @return
     */
    @RequestMapping("/simpleDemo")
    @ResponseBody
    String simpleDemo() {

        return XSS_SCRIPT_ALERT;
    }

    /**
     * 反射型XSS，
     * <p>
     * 因为连接里无法直接传递脚本，此处为了体现入参攻击，做了base64处理后进行处理。
     * <p>
     * 其实日常情况下，我们使用的加密方式传输，也会存在这种情况。
     * 比如用户名使用加密方式传输，到后台解密入库，如果不做格式校验，那么该字段被使用到的页面就存在xss注入漏洞。
     * <p>
     * http://localhost:8080/xss/reflectXSS/MTEx
     * http://localhost:8080/xss/reflectXSS/PHNjcmlwdD5hbGVydCgnaXQgaXMgYSB4c3MgdGVzdCEnKTwvc2NyaXB0Pg==
     *
     * @return
     */
    @RequestMapping("/reflectXSS/{xssStr}")
    @ResponseBody
    String reflectXSS(@PathVariable(name = "xssStr") String xssStr) {

        String xssContent = "error.";

        try {
            byte[] xssBytes = Base64.getUrlDecoder().decode(xssStr);
            xssContent = new String(xssBytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return xssContent;

    }

    /**
     *
     * 进入页面
     * http://localhost:8080/xss/xssPage
     * @return
     */
    @RequestMapping("/xssPage")
    String xssPage() {
        return "xssPage";
    }

    /**
     * 页面提交请求，不做处理，直接返回
     * http://localhost:8080/xss/ajaxReflectionXSSShow
     *
     * 反射型XSS请求
     * @return
     */
    @RequestMapping("/ajaxReflectionXSSShow")
    @ResponseBody
    User ajaxReflectionXSSShow(@RequestBody User user) {
        return user;
    }

    /**
     * 页面提交请求，处理结果并返回，避免XSS
     * http://localhost:8080/xss/ajaxWithoutReflectionXSSShow
     *
     * 反射型XSS请求
     * @return
     */
    @RequestMapping("/ajaxWithoutReflectionXSSShow")
    @ResponseBody
    User ajaxWithoutReflectionXSSShow(@RequestBody User user) {

        return null;
    }

    /**
     *
     * http://localhost:8080/xss/saveXSS
     *
     * 页面提交请求，存储后返回
     * 存储型XSS请求
     * @return
     */
    @RequestMapping("/saveXSS")
    @ResponseBody
    String saveXSS() {
        return "xssPage";
    }

    /**
     *
     * http://localhost:8080/xss/querySaveXSS
     * 直接查询存储的xss内容返回
     * @return
     */
    @RequestMapping("/querySaveXSS")
    @ResponseBody
    String querySaveXSS() {
        return "xssPage";
    }

    /**
     *
     * http://localhost:8080/xss/querySaveWithoutXSS
     * 直接查询存储的xss内容，处理后返回，避免XSS
     * @return
     */
    @RequestMapping("/querySaveWithoutXSS")
    @ResponseBody
    String querySaveWithoutXSS() {
        return "xssPage";
    }
}
