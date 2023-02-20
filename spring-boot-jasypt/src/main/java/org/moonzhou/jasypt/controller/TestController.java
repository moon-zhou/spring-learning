package org.moonzhou.jasypt.controller;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.jasypt.config.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moon zhou
 * @description file upload / download /delete
 * @email ayimin1989@163.com
 * @date 2023/2/20 21:30
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private User user;

    /**
     * http://localhost:8080/test/user
     *
     * @return
     */
    @GetMapping("/user")
    public User index() {
        // 直接返回注入的user，会报异常：No serializer found for class org.springframework.context.expression.StandardBeanExpressionResolver and no properties discovered to create BeanSerializer
        // https://www.cnblogs.com/marrycode/p/11801309.html
        User testUser = new User();
        BeanUtils.copyProperties(user, testUser);
        return testUser;
    }


}
