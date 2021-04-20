package org.moonzhou.spring.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * spring里Event事件，监听示例
 */
@Component
public class SpringContextRefreshEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("bean刷新了..." + event.getApplicationContext().getBeanDefinitionCount());

        // 获取所有的bean
        String[] definitionNames = event.getApplicationContext().getBeanDefinitionNames();
        for (String name : definitionNames) {
            // 打印名称
            System.out.println("name = " + name);
        }
    }
}