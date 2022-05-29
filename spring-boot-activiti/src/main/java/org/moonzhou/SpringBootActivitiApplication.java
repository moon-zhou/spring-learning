package org.moonzhou;

import org.activiti.core.common.spring.identity.config.ActivitiSpringIdentityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot activiti 启动类
 *
 * activiti-spring-boot默认集成了spring security用于权限管理，如需禁用security启动类中屏蔽ActivitiSpringIdentityAutoConfiguration
 * 再增加一个配置类即可 Application中禁用权限相关集成
 *
 * @author moonzhou
 */
@SpringBootApplication(exclude = {ActivitiSpringIdentityAutoConfiguration.class})
public class SpringBootActivitiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActivitiApplication.class, args);
	}

}
