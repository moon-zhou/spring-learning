/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: MyYamlPropertySourceFactory.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/6/15 14:21
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.profile.config.factory;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;
import java.util.Properties;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MyYamlPropertySourceFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource) throws IOException {
        // yml配置使用如下初始化方式
        return new YamlPropertySourceLoader().load(name, encodedResource.getResource()).get(0) ;

        //  此处与@ImportResource方式有异曲同工
        /*YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(encodedResource.getResource());
        factory.afterPropertiesSet();
        Properties ymlProperties = factory.getObject();
        String propertyName = name != null ? name : encodedResource.getResource().getFilename();
        return new PropertiesPropertySource(propertyName, ymlProperties);*/

        // DefaultPropertySourceFactory的源码里面是如下实现(此处配置无效)
//        return name != null ? new ResourcePropertySource(name, encodedResource) : new ResourcePropertySource(encodedResource);
    }


    /*private static String getNameForResource(Resource resource) {
        String name = resource.getDescription();
        if (!org.springframework.util.StringUtils.hasText(name)) {
            name = resource.getClass().getSimpleName() + "@" + System.identityHashCode(resource);
        }
        return name;
    }*/
}
