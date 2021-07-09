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

##### filter dispatcher
* REQUEST（默认值）
* FOWARD
* INCLUDE
* ERROR

拦截规则：
- REQUEST：拦截每一次客户端的的http请求，服务端转发是不会触发的(forward转发)，但是重定向是可以拦截的(302-sendRedirect)。
- FOWARD：通过request dispatcher的forward方法传递过来，则必须经过这个过滤器。（注意此示例需要配置拦截的路径为`/dispatcher/forward2`，不能配置`/dispatcher/forward1`）
- INCLUDE：通过request dispatcher的include方法传递过来，则必须经过这个过滤器
- ERROR：通过<error-page>过来的，则必须经过这个过滤器

注意事项：   
1. 在目标资源中调用forward方法时，必须保证此响应没有提交。也就是不要使用 ServletResponse 对象的输出流对象，因为即便你写入了数据到响应缓冲区，最后也会被清空，如果缓冲区数据被刷新提交（out.flush），还会抛出IllegalStateException异常。
1. 服务端转发后，目标资源使用的request对象和转发的资源使用的request对象不是同一个request对象，因为分别从这2个request中获取RequestURL，发现是不一样的。但是在目标资源request提取的`Paramter` 和 `Attribute`，在转发后的资源的request对象中，依然都可以提取到，且是相同的。所以，二者只是在请求路径相关的属性上不同，其它API调用返回的都是一样的。
1. forward1-->forward2时，虽然forward2也在RequestFilter里面进行了配置拦截，因为本身不是客户端发起，所以不会被RequestFilter拦截，只是被ForwardFilter拦截。如果直接访问forward2，则此时都会被拦截到。
1. include包含结果是string时，会拼接返回，比如此示例`org.moonzhou.filter.web.DispatcherController.include`。在被包含的资源中从request中获取请求路径相关的信息，发现依然是原始请求的路径（request uri值不变），也就是浏览器地址栏相关的路径，也就是说被包含的资源获得的request对象的路径属性和原始请求资源的路径一样。
1. forward会丢失response，但是include会将原来的数据和后来的数据合并在一起
1. 在做组件开发时，比如http请求统一解密时，需要注意的是，服务端forward或者include，只需要最初才request filter过滤处理即可，后续dispatcher是不需要再进行相同的处理即可。因此可以在request里设置标志位，进入此过滤器如果有值，说明是服务端dispatcher的请求，无需进行重复处理。


##### OncePerRequestFilter

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
1. filter dispatcher
    ```
    过滤器：
    org.moonzhou.filter.dispatcher.ForwardFilter
    org.moonzhou.filter.dispatcher.RequestFilter
    
    controller：
    org.moonzhou.filter.web.DispatcherController
    
    请求：
    单纯request：http://localhost:8081/dispatcher/request
    302request：http://localhost:8081/dispatcher/sendRedirect1
    forward:http://localhost:8081/dispatcher/forward1?userName=moonzhou
    
    注意：因为component方式拦截所有的请求，所以当前的请求也会被component方式的两个拦截器拦截。忽略这两个，关注需要关注的过滤器即可。
    ```
1. include dispatcher
    ```
    过滤器：
    org.moonzhou.filter.dispatcher.IncludeFilter
    org.moonzhou.filter.dispatcher.RequestFilter
    
    controller：
    org.moonzhou.filter.web.DispatcherController
    
    请求：
    http://localhost:8081/dispatcher/include?userName=moonzhou
    ```

#### 参考
1. [log](https://devdocs.io/spring_boot/spring-boot-features#boot-features-logging)
1. [SpringBoot过滤器Filter](https://www.jdon.com/springboot/spring-filter.html)