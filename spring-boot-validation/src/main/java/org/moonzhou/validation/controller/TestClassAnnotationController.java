package org.moonzhou.validation.controller;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

/**
 * @author moon zhou
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/testClass")
public class TestClassAnnotationController {

    /**
     * path variable check
     *
     * @param userId
     * @return
     */
    @GetMapping("/test1/{userId}")
    public String test1(@PathVariable("userId") @Min(10) Long userId) {

        log.info("test user id is: {}.", userId);

        return "success";
    }

    /**
     * path variable check
     *
     * @param userId
     * @return
     */
    @GetMapping("/test2/{userId}")
    public String test2(@PathVariable("userId") @Length(min = 3, max = 6) String userId) {

        log.info("test user id is: {}.", userId);

        return "success";
    }
}