package org.moonzhou.springbootjwt.controller;

import org.moonzhou.springbootjwt.annotation.TokenRequired;
import org.moonzhou.springbootjwt.entity.User;
import org.moonzhou.springbootjwt.result.ResultDTO;
import org.moonzhou.springbootjwt.result.ResultError;
import org.moonzhou.springbootjwt.result.UserError;
import org.moonzhou.springbootjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/5/5 21:16
 * @since 1.0
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResultDTO login(User user){

        String token = userService.login(user.getUsername(), user.getPassword());
        if (token == null) {
            return ResultDTO.failure(new ResultError(UserError.PASSWORD_OR_NAME_IS_ERROR));
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return ResultDTO.success(tokenMap);
    }

    @TokenRequired
    @GetMapping("/hello")
    public String getMessage(){
        return "登录了才能看到我哦";
    }

    @GetMapping("/hi")
    public String sayHi() {
        return "你好呀，无需登录";
    }
}
