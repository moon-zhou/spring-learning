package org.moonzhou.validation.controller;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.validation.param.StatusParam;
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
@RequestMapping("/testEnumValidation")
public class TestEnumValidationController {

    /**
     * enum validate: 0/1, others is illegal
     * @param testParam
     * @return
     */
    @PostMapping("/test")
    public String test(@Validated @RequestBody StatusParam statusParam){

        log.info("test status is: {}.", statusParam.getStatus());

        return "success";
    }
}