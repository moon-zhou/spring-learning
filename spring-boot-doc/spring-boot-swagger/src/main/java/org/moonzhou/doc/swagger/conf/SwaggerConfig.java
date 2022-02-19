package org.moonzhou.doc.swagger.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author moon-zhou <ayimin1989@163.com>
 * @version V1.0.0
 * @description
 * @date 2022/2/19 13:58
 * @since 1.0
 */
@Configuration
public class SwaggerConfig {

    @Value("${spring.profiles.active:NA}")
    private String active;

    @Bean
    public Docket createRestApi() {
        // 2.x   SWAGGER_2
        return new Docket(DocumentationType.OAS_30)
                // 仅在开发环境开启Swagger，或者通过springfox.documentation.enabled配置
                // .enable("dev".equals(active))
                .apiInfo(apiInfo())
                // Base URL
                .host("http://www.example.com")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.moonzhou.doc.swagger.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("moon zhou的springboot集成swaggerAPI文档")
                .description("moon zhou 这是描述信息")
                .contact(new Contact("moon zhou", "https://moon-zhou.github.io/", "ayimin1989@163.com"))
                .version("9.9")
                .build();
    }
}
