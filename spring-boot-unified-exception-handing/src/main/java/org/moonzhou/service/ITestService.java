package org.moonzhou.service;

import java.util.Map;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/6 20:50
 * @since 1.0
 */
public interface ITestService {

    /**
     * 测试返回字符串
     * @return
     */
    String getTestString();

    /**
     * 测试返回map对象
     * @return
     */
    Map<String, Object> getTestMap();
}
