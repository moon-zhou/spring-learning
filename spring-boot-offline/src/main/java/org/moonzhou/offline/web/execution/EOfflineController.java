package org.moonzhou.offline.web.execution;

import org.moonzhou.offline.annotation.Offline;
import org.moonzhou.offline.dto.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 表达式离线http服务示例
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2021/5/6 17:05
 * @since 1.0
 */
@RestController
@RequestMapping("/eoffline")
public class EOfflineController {


    /**
     * http://localhost:8080/eoffline/index
     *
     * @return
     */
    @RequestMapping("/index")
    String index() {
        return "hello offline project, but method not end with off, so service is still online !!!";
    }

    /**
     * http://localhost:8080/eoffline/indexOff
     *
     * @return
     */
    @RequestMapping("/indexOff")
    String indexOff() {
        return "hello offline project!!!";
    }

    /**
     * 测试返回动态页面下线
     * http://localhost:8080/eoffline/retDynamicPageOff
     *
     * 测试直接访问静态页面
     * http://localhost:8080/hello.html
     * @return
     */
    @RequestMapping("retDynamicPageOff")
    ModelAndView retDynamicPageOff() {
        // 返回动态页面，路径：resources/templates/user.html
        ModelAndView user = new ModelAndView("user");
        user.addObject("name", "moon");

        return user;
    }

    /**
     *
     * http://localhost:8080/eoffline/toAjaxPage
     *
     * 进入页面，该请求不下线，
     * 但是该页面发起两个请求：
     *     一个ajax请求，该ajax请求是下线的
     *     一个ajax请求，该ajax请求未下线
     *
     *
     *     此方法不需要，直接使用annotation的请求页面，在页面添加相关测试请求
     *
     * @return
     */
    /*@RequestMapping("/toAjaxPage")
    ModelAndView toAjaxPage() {
        ModelAndView ajaxOfflineTest = new ModelAndView("ajaxOfflineTest");

        return ajaxOfflineTest;
    }*/



    /**
     * 测试返回json数据
     * http://localhost:8080/eoffline/retJsonOff
     * @return
     */
    @RequestMapping("retJsonOff")
    @ResponseBody
    CommonResult retJsonOff() {
        CommonResult result = new CommonResult();

        return result;
    }
}
