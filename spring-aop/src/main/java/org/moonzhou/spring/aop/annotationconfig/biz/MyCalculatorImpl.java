package org.moonzhou.spring.aop.annotationconfig.biz;

import org.moonzhou.spring.aop.annotationconfig.annotation.MoonLog;
import org.springframework.stereotype.Component;

@Component
public class MyCalculatorImpl {
    @MoonLog
    public int add(int a, int b) {
        return a + b;
    }

    public void min(int a, int b) {
        System.out.println(a + "-" + b + "=" + (a - b));
    }
}