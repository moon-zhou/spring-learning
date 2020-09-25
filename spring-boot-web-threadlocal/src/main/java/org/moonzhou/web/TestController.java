package org.moonzhou.web;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.constant.GlobalConstant;
import org.moonzhou.constant.ParamsConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/6 11:35
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {


    /**
     * http://localhost:8080/test/index
     *
     * @return
     */
    @RequestMapping("/index")
    String index() {
        return "Hello World!";
    }

    /**
     * http://localhost:8080/test/testAutoFilter
     * 测试自动装载的filter里设置的值：可以拿到
     *
     * @return
     */
    @RequestMapping("testAutoFilter")
    Map<String, Object> testAutoFilter() {

        Map<String, Object> result = new HashMap<>();

        String msg = GlobalConstant.CURRENT_THREAD_REQUEST.get().getAttribute(ParamsConstant.AUTH_FILTER_PARAM_KEY_NAME) + "---"
                + GlobalConstant.CURRENT_THREAD_REQUEST.get().getAttribute(ParamsConstant.MONITOR_FILTER_PARAM_KEY_NAME);

        result.put("responseCode", "0000");
        result.put("responseMsg", msg);

        return result;
    }

    /**
     * http://localhost:8080/test/testOtherThreadGetParam
     * 测试从其他线程里面拿自动装载的filter里设置的值，不能拿到
     *
     * @return
     */
    @RequestMapping("testOtherThreadGetParam")
    Map<String, Object> testOtherThreadGetParam() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Map<String, Object> result = new HashMap<>();


        // 单开一个线程是获取不到ThreadLocal里的值的
        new Thread(() -> {

            ServletRequest servletRequest = GlobalConstant.CURRENT_THREAD_REQUEST.get();

            log.info("获取filter里设置的ThreadLocal值为：{}.", servletRequest);

            if (null == servletRequest) {
                result.put("responseCode", "0001");
                result.put("responseMsg", "该线程里无ThreadLocal变量");
            } else {
                result.put("responseCode", "0000");
                result.put("responseMsg", servletRequest.getAttribute(ParamsConstant.AUTH_FILTER_PARAM_KEY_NAME));
            }

            countDownLatch.countDown();
        }).start();

        countDownLatch.await();

        log.info("testOtherThreadGetParam end...");

        return result;
    }
}
