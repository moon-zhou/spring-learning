package org.moonzhou.validation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moon zhou
 */
@Slf4j
@RestController
@RequestMapping("/testPathVariable")
public class TestPathVariableController {

    /**
     * path variable valid
     * 请求路径中的id必须是数字，否则寻找不到这个路径404
     * @param userId
     * @return
     */
    @GetMapping("/user/{userId:\\d+}")
    public String getId(@PathVariable(name="userId") String userId) {
        return "success";
    }

}