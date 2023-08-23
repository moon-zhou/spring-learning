package org.moonzhou.biz.multiimpllist.service;

public interface TrafficResolver {

    boolean support(String type);

    Object resolver(Object argument);
}
