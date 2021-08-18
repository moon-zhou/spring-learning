/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: DispatcherController.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/7/6 9:44
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.filter.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping("/dispatcher")
public class DispatcherController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherController.class);

    /**
     * http://localhost:8081/dispatcher/request
     *
     * @return
     */
    @RequestMapping("/request")
    String request() {
        LOGGER.info("dispatcher request controller.");

        return "dispatcher request controller.";
    }





    /**
     * http://localhost:8081/dispatcher/sendRedirect1
     *
     * @param request
     * @param response
     */
    @RequestMapping("/sendRedirect1")
    void sendRedirect1(HttpServletRequest request, HttpServletResponse response) {
        try {
            LOGGER.info("dispatcher sendRedirect1 controller.");

            request.setAttribute("redirect", UUID.randomUUID());
            LOGGER.info("request refer: {}, {}.", request.getHeader("referrer"),
                    request.getAttribute("redirect"));

            response.sendRedirect("/dispatcher/sendRedirect2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * http://localhost:8081/dispatcher/sendRedirect2
     *
     * @param request
     * @param response
     */
    @RequestMapping("/sendRedirect2")
    String sendRedirect2(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("dispatcher sendRedirect2 controller.");

        LOGGER.info("request refer: {}, {}.", request.getHeader("referrer"),
                request.getAttribute("redirect"));
        return "dispatcher redirect1-redirect2 controller";
    }





    /**
     * http://localhost:8081/dispatcher/forward1?userName=moonzhou
     *
     * @return
     */
    @RequestMapping("/forward1")
    void forward1(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("dispatcher forward1 controller.");

        try {
            LOGGER.info("request memory address: {}.", request);

            request.setAttribute("forwardValue", UUID.randomUUID());
            LOGGER.info("request refer: {}, {}, {}.", request.getHeader("referrer"),
                    request.getAttribute("forwardValue"), request.getParameter("userName"));

            request.getRequestDispatcher("/dispatcher/forward2").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * http://localhost:8081/dispatcher/forward2
     *
     * @return
     */
    @RequestMapping("/forward2")
    String forward2(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("dispatcher forward2 controller.");

        // request不是一个
        LOGGER.info("request memory address: {}.", request);

        // 服务端转发，refer是第一次请求的refer，但是参数依然都能够获取到
        LOGGER.info("request refer: {}, {}, {}.", request.getHeader("referrer"),
                request.getAttribute("forwardValue"), request.getParameter("userName"));


        return "dispatcher forward1-forward2 controller";
    }





    /**
     * http://localhost:8081/dispatcher/include?userName=moonzhou
     *
     * @return
     */
    @RequestMapping("/include")
    void include(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("dispatcher include controller.");

        try {
            LOGGER.info("request memory address: {}.", request);

            request.setAttribute("forwardValue", UUID.randomUUID());
            LOGGER.info("request refer: {}, uri: {}, {}, {}.", request.getHeader("referrer"), request.getRequestURI(),
                    request.getAttribute("forwardValue"), request.getParameter("userName"));

            request.getRequestDispatcher("/dispatcher/include1").include(request, response);
            request.getRequestDispatcher("/dispatcher/include2").include(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * http://localhost:8081/dispatcher/include1
     *
     * @return
     */
    @RequestMapping("/include1")
    String include1(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("dispatcher include1 controller.");

        // request不是一个
        LOGGER.info("request memory address: {}.", request);

        // 服务端包含，refer是第一次请求的refer，但是参数依然都能够获取到
        LOGGER.info("request refer: {}, uri: {}, {}, {}.", request.getHeader("referrer"), request.getRequestURI(),
                request.getAttribute("forwardValue"), request.getParameter("userName"));


        return "dispatcher include-include1 controller";
    }

    /**
     * http://localhost:8081/dispatcher/include2
     *
     * @return
     */
    @RequestMapping("/include2")
    String include2(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("dispatcher include2 controller.");

        // request不是一个
        LOGGER.info("request memory address: {}.", request);

        // 服务端包含，refer是第一次请求的refer，但是参数依然都能够获取到
        LOGGER.info("request refer: {}, uri: {}, {}, {}.", request.getHeader("referrer"), request.getRequestURI(),
                request.getAttribute("forwardValue"), request.getParameter("userName"));


        return "dispatcher include-include2 controller";
    }



}
