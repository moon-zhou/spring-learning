package org.moonzhou.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.dto.TestDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 无注解方式获取参数
 *
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/14 11:14
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/testNoAnnotationParam")
public class TestNoAnnotationController {
    /**
     * http://localhost/testNoAnnotationParam/index
     *
     * @return
     */
    @RequestMapping("/index")
    String index() {
        return "Hello World!";
    }

    /**
     * http://localhost/testNoAnnotationParam/testDtoParam
     * <p>
     * 1. 日期类型字段需要添加<code>@DateTimeFormat(pattern = "yyyy-MM-dd")</code>
     * 2. get请求，参数直接通过连接传递可以映射成功
     * 3. 通过form-data也可以映射成功
     * 4. body里raw方式时不能映射进来的
     *
     * @param testDto
     * @return
     */
    @RequestMapping("testDtoParam")
    public TestDto testDtoParam(TestDto testDto) {

        return testDto;
    }

    /**
     * http://localhost/testNoAnnotationParam/testMapParam
     * <p>
     * 1. map因为没有key，无法接受参数
     *
     * @param params
     * @return
     */
    @RequestMapping("testMapParam")
    public Map<String, Object> testMapParam(Map<String, Object> params) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        log.info("map param is: {}.", mapper.writeValueAsString(params));

        return params;
    }

    /**
     * http://localhost/testNoAnnotationParam/testOriginalParam
     * <p>
     * 1. 依然只支持链接传参和body里form-data方式，不能通过body里raw方式
     *
     * @param request
     * @return
     */
    @RequestMapping("testOriginalParam")
    public TestDto testOriginalParam(HttpServletRequest request) {

        String userName = request.getParameter("userName");
        int age = Integer.parseInt(request.getParameter("age"));
        boolean handSome = Boolean.parseBoolean(request.getParameter("handSome"));
        Long id = Long.valueOf(request.getParameter("id"));
        String birthDay = request.getParameter("birthday");

        // 2020-6-14此格式使用时分秒解析会异常
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = new Date();
        try {
            birthday = sdf.parse(birthDay);
        } catch (ParseException e) {
            log.error("parse date error.", e);
        }

        log.info("param is: {}, {}, {}, {}, {}.", userName, age, handSome, id, birthDay);

        TestDto testDto = new TestDto(userName, age, handSome, id, birthday);
        return testDto;
    }
}
