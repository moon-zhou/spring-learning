package org.moonzhou.fiaa.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MyExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public String allExceptionHandler(Exception e) {
        // 捕获之后打印异常信息
        log.error("my exception: ", e);
        return "系统异常，请稍后再试";
    }

}