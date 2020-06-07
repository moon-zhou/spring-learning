package org.moonzhou.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.moonzhou.exception.version1.asserter.ResponseEnum;
import org.moonzhou.i18n.I18nAnnotationService;
import org.moonzhou.i18n.I18nService;
import org.moonzhou.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
     * 模拟业务逻辑处理
     */
    @Autowired
    private ITestService testService;

    /**
     * 获取国际化信息（配置bean）
     */
    @Autowired
    private I18nService i18nService;

    /**
     * 使用直接注解方式加载国际化信息
     */
    @Autowired
    private I18nAnnotationService i18nAnnotationService;

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
     * http://localhost:8080/test/helloi18n
     * http://localhost:8080/test/helloi18n?language=en_US
     * http://localhost:8080/test/helloi18n?language=zh_CN
     * <p>
     * 传参则会设置对应语言，同时返回该语言对应文案，不传则使用已经设置的
     *
     * @return
     */
    @RequestMapping(value = "helloi18n")
    Map<String, Object> helloI18n() {
        Locale locale = LocaleContextHolder.getLocale();
        log.info("=======locale is: {}.", locale);

        final HashMap<String, Object> result = new HashMap<>();

        if (StringUtils.equals("en", locale.getLanguage())) {
            result.put("en_US", i18nService.getMessage("message.key.test", locale));
        } else if (StringUtils.equals("zh", locale.getLanguage())) {
            result.put("zh_CN", i18nService.getMessage("message.key.test", locale));
        } else {
            result.put("defaultLanguage", i18nService.getMessageDefault("message.key.test"));
        }

        return result;
    }

    /**
     * http://localhost:8080/test/helloAnnotationI18n
     * http://localhost:8080/test/helloAnnotationI18n?language=en_US
     * http://localhost:8080/test/helloAnnotationI18n?language=zh_CN
     *
     * @return
     */
    @RequestMapping(value = "helloAnnotationI18n")
    Map<String, Object> helloAnnotationI18n() {

        Locale locale = LocaleContextHolder.getLocale();
        log.info("=======locale is: {}.", locale);

        final HashMap<String, Object> result = new HashMap<>();

        if (StringUtils.equals("en", locale.getLanguage())) {
            result.put("en_US", i18nAnnotationService.getMessage("message.key.hello", new String[]{"moon"}, locale));
        } else if (StringUtils.equals("zh", locale.getLanguage())) {
            result.put("zh_CN", i18nAnnotationService.getMessage("message.key.hello", new String[]{"敏少"}, locale));
        } else {
            result.put("defaultLanguage", i18nAnnotationService.getMessageDefault("message.key.test"));
        }

        return result;
    }

    /**
     * http://localhost:8080/test/testString
     *
     * @return
     */
    @RequestMapping("/testString")
    String testString() {
        return testService.getTestString();
    }

    /**
     * http://localhost:8080/test/testMap
     *
     * @return
     */
    @RequestMapping("testMap")
    Map<String, Object> testMap() {
        return testService.getTestMap();
    }

    /**
     * http://localhost:8080/test/testNPE
     *
     * @return
     */
    @RequestMapping("testNPE")
    String testNPE() {
        throw new NullPointerException("test NPE!!!");
    }

    /**
     * http://localhost:8080/test/testRuntimeException
     *
     * @return
     */
    @RequestMapping("testRuntimeException")
    String testRuntimeException() {
        throw new RuntimeException("test runtime exception!!!");
    }

    /**
     * 抛异常：
     * http://localhost:8080/test/testAssert01Exception/0
     * <p>
     * 正常请求：
     * http://localhost:8080/test/testAssert01Exception/1
     *
     * @param id
     * @return
     */
    @RequestMapping("testAssert01Exception/{id}")
    Map<String, Object> testAssert01Exception(@PathVariable("id") String id) {

        if (StringUtils.equals("0", id)) {
            id = "";
            ResponseEnum.PARAM_NOT_NULL.assertNotBlank(id);
        }

        return testService.getTestMap();
    }
}
