package org.moonzhou.biz.multiimpllist.service.impl;

import org.moonzhou.biz.multiimpllist.service.TrafficResolver;
import org.springframework.stereotype.Service;

@Service
public class BusTrafficResolver implements TrafficResolver {

    @Override
    public boolean support(String type) {
        return "BUS".equals(type);
    }

    @Override
    public Object resolver(Object argument) {
        // doSth
        return "bus resolver";
    }
}
