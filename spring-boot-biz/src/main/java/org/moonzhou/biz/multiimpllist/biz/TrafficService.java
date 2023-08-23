package org.moonzhou.biz.multiimpllist.biz;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.biz.multiimpllist.service.TrafficResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TrafficService {

    // required = false
    @Autowired(required = false)
    List<TrafficResolver> trafficResolverList = new ArrayList<>();

    public Object traffic(String type, Object argument) {
        log.info("trafficResolverList size: {}", trafficResolverList.size());
        TrafficResolver resolver = getFirstResolver(type);
        if (resolver != null) {
            return resolver.resolver(argument);
        }

        return "traffic not find. type: " + type;
    }

    private TrafficResolver getFirstResolver(String type) {
        TrafficResolver resolver = trafficResolverList.stream()
                .filter(item -> item.support(type))
                .findFirst()
                .orElse(null);
        return resolver;
    }

}
