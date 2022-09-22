package org.moonzhou.validation.controller;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.validation.param.TestParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author moon zhou
 */
@Slf4j
@RestController
@RequestMapping("/testMethod")
public class TestMethodController {

    /**
     * no valid
     * @param testParam
     * @return
     */
    @PostMapping("/testNoValid")
    public String testNoValid(@RequestBody TestParam testParam){

        log.info("test id num is: {}, {}.", testParam.getId(), testParam.getIdNum());

        return "success";
    }

    /**
     * annotation Valid
     * @param testParam
     * @return
     */
    @PostMapping("/testValid")
    public String testValid(@Valid @RequestBody TestParam testParam){

        log.info("test id num is: {}, {}.", testParam.getId(), testParam.getIdNum());

        return "success";
    }

    /**
     * annotation Validated
     * @param testParam
     * @return
     */
    @PostMapping("/testValidated")
    public String testValidated(@Validated @RequestBody TestParam testParam){

        log.info("test id num is: {}, {}.", testParam.getId(), testParam.getIdNum());

        return "success";
    }
}