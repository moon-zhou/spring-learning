### Springboot Filter
本项目为`Springboot`下，`Filter`的使用，包含多`Filter`的调用链过程，请求的公共处理，包装返回，以及`Request dispatcher`下`Filter`的不同表现示例。

#### log
* 日志路径/名称配置
    ```
    logging.file.name 可以指定路径和log文件的名字
    logging.file.path 只可以只当log的路径, 不能指定log的名字, 使用缺省值spring.log
    
    二者只可以存在一个，如果同时配置，只有name生效，path是不生效的。
    ```
* 日志级别配置：注意分组小技巧的使用
* 日志格式配置
    ```
    %d{HH:mm:ss.SSS}：日志输出时间
    %-5level：日志级别，并且使用 5 个字符靠左对齐
    %thread：输出日志的进程名字，这在 Web 应用以及异步任务处理中很有用
    %logger：日志输出者的名字
    %msg：日志消息
    %n：平台的换行符
    ```
* 日志备份
* 使用外部配置文件进行自定义的配置

| Logging System          | Customization                                                |
| :---------------------- | :----------------------------------------------------------- |
| Logback                 | `logback-spring.xml`, `logback-spring.groovy`, `logback.xml`, or `logback.groovy` |
| Log4j2                  | `log4j2-spring.xml` or `log4j2.xml`                          |
| JDK (Java Util Logging) | `logging.properties`                                         |

#### 过滤器-Filter
##### 实现方式
1. `@Component` 注解：**无法改变默认的URL映射（拦截所有请求）**，通过`@Order`注释设置过滤器顺序。
1. 使用`FilterRegistrationBean`类：提供增加URL映射的方法，设置过滤器顺序。
1. `@ServletComponentScan`注解： 过滤器必须使用`@WebFilter`注释，能够通过它的urlPattern属性增加URL映射，但是**无法设置过滤器顺序**，只在使用嵌入服务器才有效。

#### 示例运行
1. `@Component`方式：
    ```
    过滤器：
    org.moonzhou.filter.component.ComponentFilter01
    org.moonzhou.filter.component.ComponentFilter02
    
    请求：http://localhost:8081/filter/componentfilter
    ```
1. `FilterRegistrationBean`注册方式：
    ```
    过滤器：
    org.moonzhou.filter.registration.RegistrationFilter01
    org.moonzhou.filter.registration.RegistrationFilter02
    
    初始化：
    org.moonzhou.filter.config.AppConfig
   
    请求：http://localhost:8081/filter/registrationfilter
    注意：因为component方式拦截所有的请求，所以当前的请求也会被component方式的两个拦截器拦截。
    ```
1. `@WebFilter` + `@ServletComponentScan`方式：
    ```
    过滤器：
    org.moonzhou.filter.webfilter.WebFilter01
    org.moonzhou.filter.webfilter.WebFilter02
   
    scan:
    org.moonzhou.filter.SpringBootFilterApplication
    
    请求：http://localhost:8081/filter/webfilter
    注意：因为component方式拦截所有的请求，所以当前的请求也会被component方式的两个拦截器拦截。
    ```


#### 参考
1. [log](https://devdocs.io/spring_boot/spring-boot-features#boot-features-logging)
1. [SpringBoot过滤器Filter](https://www.jdon.com/springboot/spring-filter.html)