package org.moonzhou.biz.grayboxtest.biz.controller;

import org.moonzhou.biz.grayboxtest.backend.dto.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moon zhou
 * @version 1.0
 * @description: biz controller
 * @date 2023/02/23 15:55
 */
@Component
@RestController
@RequestMapping("/api/v1/biz")
public class BizTestController {


    /**
     *
     * @return
     */
    @GetMapping("/index")
    public Result<Boolean> queryGrayBoxTestStatus() {
        return Result.success(true);
    }

}
