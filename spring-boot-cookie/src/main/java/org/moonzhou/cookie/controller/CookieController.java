package org.moonzhou.cookie.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

/**
 * @author moon zhou
 * @version 1.0
 * @description: response cookie
 * @date 2022/12/6 13:12
 */
@RestController
@RequestMapping("/cookie")
public class CookieController {

    /**
     * http://localhost:8080/cookie/test/1
     * http://localhost:8080/cookie/test/2
     *
     * @param request
     * @param response
     * @param type
     * @return
     */
    @RequestMapping("/test/{type}")
    public String test(HttpServletRequest request,
                       HttpServletResponse response, @PathVariable("type") String type) {

        switch (type) {
            case "1":
                setResponseCookie1(response);
                break;
            case "2":
                setResponseCookie2(response);
                break;
            default:
                break;
        }


        return "test response cookie!!!";
    }

    private void setResponseCookie1(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("moon1", "HandsomeBoy") // key & value
                // .httpOnly(true)        // 禁止js读取
                // .secure(false)        // 在http下也传输
                .domain("localhost")// 域名
                .path("/")            // path
                .maxAge(Duration.ofHours(1))    // 1个小时候过期
                .sameSite("Lax")    // 大多数情况也是不发送第三方 Cookie，但是导航到目标网址的 Get 请求除外
                .build();

        // 设置Cookie Header
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    private void setResponseCookie2(HttpServletResponse response) {
        // 创建一个 cookie对象
        Cookie cookie = new Cookie("moon1", "HandsomeBoy");

        //将cookie对象加入response响应
        response.addCookie(cookie);
    }

}
