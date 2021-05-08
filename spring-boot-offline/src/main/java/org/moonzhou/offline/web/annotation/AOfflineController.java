package org.moonzhou.offline.web.annotation;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.offline.annotation.Offline;
import org.moonzhou.offline.dto.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 注解下线http服务示例
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2021/5/6 17:05
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/aoffline")
public class AOfflineController {


    /**
     * 测试直接返回字符串内容
     * http://localhost:8080/aoffline/retStr
     *
     * @return
     */
    @Offline
    @RequestMapping("/retStr")
    String retStr() {

        log.info("offline demo: retstr!!!");

        return "hello offline project!!!";
    }

    /**
     * 测试注解值配置未false
     * http://localhost:8080/aoffline/retStrOnline
     * @return
     */
    @Offline(offline=false)
    @RequestMapping("/retStrOnline")
    String retStrOnline() {
        return "hello offline plugin, annotation property is false!!!";
    }

    /**
     *
     * http://localhost:8080/aoffline/toAjaxPage
     *
     * 进入页面，该请求不下线，
     * 但是该页面发起两个请求：
     *     一个ajax请求，该ajax请求是下线的
     *     一个ajax请求，该ajax请求未下线
     * @return
     */
    @Offline(offline=false)
    @RequestMapping("/toAjaxPage")
    ModelAndView toAjaxPage() {
        ModelAndView ajaxOfflineTest = new ModelAndView("ajaxOfflineTest");

        return ajaxOfflineTest;
    }

    /**
     * 测试返回动态页面
     * http://localhost:8080/aoffline/retDynamicPage
     *
     * 测试直接访问静态页面
     * http://localhost:8080/hello.html
     * @return
     */
    @Offline
    @RequestMapping("retDynamicPage")
    ModelAndView retDynamicPage() {
        // 返回动态页面，路径：resources/templates/user.html
        ModelAndView user = new ModelAndView("user");
        user.addObject("name", "moon");

        return user;
    }

    /**
     * 测试返回json数据
     * http://localhost:8080/aoffline/retJson
     * @return
     */
    @Offline
    @RequestMapping("retJson")
    @ResponseBody
    CommonResult retJson() {
        CommonResult result = new CommonResult();

        return result;
    }
}
