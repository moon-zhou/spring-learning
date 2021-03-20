package org.moonzhou.xss.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 用于监听记录，xss攻击之后，将用户的cookie信息构造请求，发送给攻击者
 * 攻击者可以存储下来或者即时利用这些数据进行二次攻击
 *
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2021/3/19 17:35
 * @since 1.0
 */
@RestController
@RequestMapping("/listener")
public class ListenerController {


    /**
     * http://localhost:8080/listener/log
     *
     * 打印攻击获取到的数据
     * @return
     */
    @RequestMapping("/log")
    void log(String msg) {

        // 此处仅输出到控制台
        System.out.println("listening...");
        System.out.println(msg);
    }

    /**
     * http://localhost:8080/listener/store
     * 
     * 存储攻击的数据
     */
    @RequestMapping("/store")
    void store() {

    }
}
