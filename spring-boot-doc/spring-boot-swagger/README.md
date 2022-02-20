### springboot 集成 Swagger

#### 背景
因为最近换了公司，从原来公司的2C业务转到现在的2B业务，主要开发公司内部的HR业务系统。接触到的系统不少使用到了Swagger，因此单独写个示例，方便后续的一些沉淀。

主要使用3.0版本进行学习和测试。

#### Swagger简介
Swagger 是一个方便 API 开发的框架，它有以下优点：
- 自动生成在线文档，后端开发人员的改动可以立即反映到在线文档，并且不用单独编写接口文档，减轻了开发负担
- 支持通过 Swagger 页面直接调试接口，方便前端开发人员进行测试

#### 集成步骤
1. 引入依赖
  ```
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-boot-starter</artifactId>
        <version>3.0.0</version>
    </dependency>
  ```
1. 为项目开启 `Swagger`：启动类上添加`@EnableOpenApi`，具体见类`org.moonzhou.doc.swagger.SpringBootSwaggerApplication`
2. 创建 `SwaggerConfig` 配置类：`org.moonzhou.doc.swagger.conf.SwaggerConfig`
3. 访问 `Swagger` 前端页面：http://localhost:8081/swagger-ui/


#### 具体注解使用
1. `@Api`：将一个类标记为 `Swagger` 资源，一般放在 `Controller` 类上面，通过 `tags` 指定描述信息，比如 `@Api(tags="用户管理")`。
2. `@ApiOperation`：本注解放在 `Controller` 方法上面，描述该方法的作用。
3. `@ApiParam`：本注解放在 `Controller` 方法的形参前面，可以描述参数的作用，比如 `@ApiParam("用户名")` String username。可以使用 `value` 指定描述信息，通过 `required = true` 指定必需传递该参数。


#### 参考
1. [在 Spring Boot 中使用 Swagger](https://juejin.cn/post/6992515756504121380)
2. [还在手动整合Swagger？Swagger官方Starter是真的香！](https://juejin.cn/post/6890692970018766856)