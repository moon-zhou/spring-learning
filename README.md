### spring学习集成项目
此项目为日常spring里知识点测试，以及相关spring在实际开发过程中的实现方案示例。

#### 目录
1. `spring-boot-unified-exception-handing`:统一异常处理
2. 国际化支持:TODO尚未开始
3. `spring-boot-testing`:测试模块
    > 性能测试某些方法，某些算法
4. `spring-boot-profile`：springboot区分环境时的一些配置或特性
5. `spring-boot-logback`:logback使用集成
6. `spring-boot-request-param-mapping`:http请求参数接收示例
7. `spring-boot-response`:http接口返回参数示例
8. `spring-boot-web-threadlocal`: threadlocal实际使用
9. `spring-boot-filter`：`Servlet Filter`的相关使用，用于一些`Filter`的组件开发。
10. `spring-boot-jwt`：`jwt`在`springboot`下的示例
11. `spring-boot-doc`：spingboot里关于接口的处理
    - `spring-boot-swagger`：springboot中集成swagger3
12. `spring-boot-pdf`：springboot集成pdf工具
13. `spring-boot-mybatis-plus`：springboot中集成mybatis-plus
14. `spring-boot-mybatis-plus-advance`：mybatis-plus的高级特性
    1. 多数据源
    2. 动态表名
    3. 数据安全保护：配置信息加密
15. `spring-boot-jpa：springboot中集成jpa
16. `spring-boot-session`：springboot集成spring session
17. `spring-boot-cache`：springboot集成spring cache
18. `spring-boot-pg-connection：pgsql连接之后，数据库服务关闭客户端应用连接后，应用的反应，是否会重连等
19. `spring-boot-aspectJ`：业务中使用aspectJ，以及改变返回值验证
20. `spring-boot-validation`：TODO 验证及自定义验证
21. `spring-boot-transaction`：事务使用（正确&错误的使用方式导致事务失效）
22. `spring-boot-file`：TODO 文件上传下载（断点续传等）
23. `spring-boot-threadpool`：添加线程池使用示例
24. `spring-boot-threadpool-actuator`：线程池Actuator监控
25. `spring-boot-httputil`：TODO http工具使用，httpclient调用httpserver接口
26. `spring-boot-activiti`：springboot集成activiti
27. `spring-boot-redis`：springboot集成redis
28. `spring-boot-biz`： spring boot 验证biz上的一些best practice
29. `spring-boot-event`：spring boot 集成event
30. `spring-boot-init`：spring boot 应用启动过程中，本身的一些配置或者触发事件
31. `spring-boot-netty`：spring boot集成netty，使用netty实现消息推送
32. `spring-boot-security：spring boot 集成security
33. `spring-boot-cookie`： spring boot 测试返回cookie的设置
34. `spring-boot-jasypt`：jasypt配置加解密
35. `spring-boot-j2cache`：springboot 集成j2cache
36. `spring-boot-jdk-introspector`：java bean 内省机制
37. `spring-boot-fiaa`：Filter Interceptor AOP Advice 执行顺序
38. 安全问题：
     * `spring-boot-sql-injection`:sql注入
     * `spring-boot-xss`:xss注入
     * `spring-boot-csrf`:csrf注入
     * `spring-boot-log4j-jndi-bug`: `log4j jndi`漏洞演示及修复
39. spring学习:
     * `spring-ioc`
     * `spring-aop`
     * `spring-transaction`:TODO 尚未开始
     * `spring-event`:spring事件
40. 公共组件类
     * `spring-boot-offline`：基于aspectJ的http接口下线组件