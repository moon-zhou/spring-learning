package org.moonzhou.service.impl;

import org.moonzhou.service.ITestService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2020/6/6 20:51
 * @since 1.0
 */
@Service
public class TestServiceImpl implements ITestService {
    @Override
    public String getTestString() {
        return "hello handsome boy!";
    }

    @Override
    public Map<String, Object> getTestMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", "moon zhou");
        result.put("age", 18);
        result.put("handsome", true);
        result.put("date", new Date());

        return result;
    }
}
