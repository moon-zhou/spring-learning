package org.moonzhou.validation.controller;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.validation.param.TestParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moon zhou
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
 
    @PostMapping("/testIdNum")
    public String test(@Validated @RequestBody TestParam testParam){

        log.info("test id num is: {}, {}.", testParam.getId(), testParam.getIdNum());

        return "success";
    }
}