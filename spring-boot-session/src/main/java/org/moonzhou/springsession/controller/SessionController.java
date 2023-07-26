package org.moonzhou.springsession.controller;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.springsession.bean.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author moon zhou
 */
@Slf4j
@RequestMapping("/spring-session")
@RestController
public class SessionController {

    /**
     * http://localhost:8080/spring-session/save
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save")
    public Map<String, Object> getSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("username", "moon");

        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", session.getId());
        map.put("content", session.getAttribute("username").toString());

        return map;
    }

    /**
     * http://localhost:8080/spring-session/get
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get")
    public String get(HttpServletRequest request) {
        Object username = request.getSession().getAttribute("username");
        return null == username ? "null" : username.toString();
    }

    /**
     * http://localhost:8080/spring-session/mock-login-biz?username=zhou
     * @param username
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/mock-login-biz")
    public Object springSession(@RequestParam("username") String username, HttpServletRequest request, HttpSession session) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                log.warn(cookie.getName() + "=" + cookie.getValue());
            }
        }

        Object value = session.getAttribute("user");
        if (value == null) {
            log.warn("用户不存在");
            User user = new User(username, 18);
            // 保存session
            session.setAttribute("user", user);
            return "user not exist!!!";
        } else {
            log.warn("用户存在");
            return "username=" + value.toString();
        }
    }

}