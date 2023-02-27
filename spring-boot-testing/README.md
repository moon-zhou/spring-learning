### 专用于测试的模块
1. 测试日志打印：问题
    1. 调整日志打印级别为debug之后，会报如下错误
        ```
        org.apache.tomcat.jni.LibraryNotFoundError: Can't load library: ...\tcnative-1.dll
        ```
    1. 原因为：因为Tomcat中的connector为了提高性能，采用了加载与操作系统绑定（非跨平台）的本地库的方式，比如Windows系统中就是.dll动态链接库。上述异常中找不到的两个.dll库文件，默认会去Tomcat的bin目录下去找，但是由于SpringBoot的Tomcat是嵌入式的，没有这两个.dll，所以只好自己去官网下载，到如下官方地址下载，并放到C:\Windows\System32\目录下：
       ```
           http://archive.apache.org/dist/tomcat/tomcat-connectors/native/1.2.14/binaries/
       ```
2. 单纯输出日志时，占位符方式，比append之后直接输出耗时高一丢丢

### springboot test
#### 常用注解
| 注解                    | 作用                                                             |
| --------------------- | -------------------------------------------------------------- |
| @SpringBootTest       | 用于指定测试类启用Spring Boot Test，默认会提供Mock环境                          |
| @ExtendWith           | 如果只想启用Spring环境进行简单测试，不想启用Spring Boot环境，可以配置扩展为：SpringExtension |
| @Test                 | 指定方法为测试方法                                                      |
| @TestMethodOrder      | 用于配置测试类中方法的执行顺序策略，配置为OrderAnnotation时，按@Order顺序执行              |
| @Order                | 用于配置方法的执行顺序，数字越低执行顺序越高                                         |
| @DisplayName          | 用于指定测试类和测试方法的别名                                                |
| @BeforeAll            | 在测试类的所有测试方法前执行一次，可用于全局初始化                                      |
| @AfterAll             | 在测试类的所有测试方法后执行一次，可用于全局销毁资源                                     |
| @BeforeEach           | 在测试类的每个测试方法前都执行一次                                              |
| @AfterEach            | 在测试类的每个测试方法后都执行一次                                              |
| @Disabled             | 禁用测试方法                                                         |
| @RepeatedTest         | 指定测试方法重复执行                                                     |
| @ParameterizedTest    | 指定参数化测试方法，类似重复执行，从@ValueSource中获取参数                            |
| @ValueSource          | 用于参数化测试指定参数                                                    |
| @AutoConfigureMockMvc | 启用MockMvc的自动配置，可用于测试接口                                         |

#### 添加依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

### 参考
1. [别再用main方法测试了，太Low！这才是专业的SpringBoot项目测试方法！](https://juejin.cn/post/7158258490501234695)