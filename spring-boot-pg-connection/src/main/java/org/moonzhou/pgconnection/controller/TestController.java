package org.moonzhou.pgconnection.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.pgconnection.model.User;
import org.moonzhou.pgconnection.service.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moon zhou
 * @description
 * @email ayimin1989@163.com
 * @date 2022/4/24 11:03
 **/
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {


    // inject by constructor method in spring context, annotation @AllArgsConstructor,
    private final UserServiceImpl userService;

    /**
     * http://localhost:8080/test/index
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "hello spring boot pg connection test!!!";
    }

    /**
     * http://localhost:8080/test/get-user
     *
     * @return
     */
    @RequestMapping("/get-user")
    public User getUser() {

        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        wrapper.gt(User::getAge, 28);
        User user = userService.getOne(wrapper, false);

        return user;
    }


}
