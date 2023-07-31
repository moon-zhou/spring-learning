package org.moonzhou.pgconnection.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.pgconnection.model.User;
import org.moonzhou.pgconnection.service.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author moon zhou
 * @description 监控：http://localhost:8080/actuator/prometheus
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

    /**
     * JDBC Connection [HikariProxyConnection@1656186127 wrapping org.postgresql.jdbc.PgConnection@605683e4] will not be managed by Spring
     * http://localhost:8080/test/save-user
     *
     * @return
     */
    @RequestMapping("/save-user")
    public User saveUser() {

        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        wrapper.eq(User::getAge, 28);
        User user = userService.getOne(wrapper, false);
        user.setId(null);
        user.setAge(user.getAge() + 1);
        user.setCreateTime(LocalDateTime.now());
        userService.save(user);

        return user;
    }

    /**
     * JDBC Connection [HikariProxyConnection@1074628625 wrapping org.postgresql.jdbc.PgConnection@7d99a22a] will be managed by Spring
     * http://localhost:8080/test/custom-save-user
     *
     * @return
     */
    @RequestMapping("/custom-save-user")
    public User customSaveUser() {

        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        wrapper.eq(User::getAge, 28);
        User user = userService.getOne(wrapper, false);
        user.setId(null);
        user.setAge(user.getAge() + 1);
        user.setCreateTime(LocalDateTime.now());
        userService.customSave(user);

        return user;
    }


}
