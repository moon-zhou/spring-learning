## SpringBoot Logback使用

### 日志打印规则
1. 按日志类别分
    * 业务日志（按业务分文件打印）
    * 监控日志（拦截器分层打印-controller/services/rpc）
2. 按日志格式（总体有个格式-项目名、部署集群名、IP、流水号、用户id、sessionid、类名，此处为内容隐位格式）
    * 超长日志隐位/丢弃
    * 敏感信息脱敏

### 主要类容
1. 基本使用（参数配置）
2. MDC自定义参数
3. 分层打印
4. 数据脱敏
5. 审计日志

### 使用springboot配置
1. 配置日志级别
    ```
    logging:
      level:
       root: info
    ```

### Logback使用
1. 核心配置结构
    ```
    configuration
    ├─contextName
    ├─property
    ├─appender
    ├─logger
    └─root
    ``` 
2. configuration配置(属性如下)
    * debug：默认为false，若设置为true，则打印出logback内部日志信息
    * scan：默认值为true，若设置为true，配置文件如果发生改变，将会被重新加载。
    * 与scan配合使用，当scan为true时，此属性生效，默认的时间间隔为1分钟，设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。如可以设置为`scanPeriod="30 seconds"`每30秒检测一次。
    ```
    <configuration scan="true" scanPeriod="1 seconds">
    ```
3. contextName：日志名，可以使用`%contextName`来引用
4. appender
   > appender让我们的应用知道怎么打、打印到哪里、打印成什么样；而logger则是告诉应用哪些可以这么打。
    * ConsoleAppender(输出到控制台)
    * FileAppender(输出到文件)
    * RollingFileAppender(先将日志输出到指定文件，满足一定条件后将日志记录到其他文件中)
5. property
   > 用来定义变量值的标签，property标签有两个属性，name和value。定义变量后，可以使`${name}`来使用变量。
6. logger
   > 子Logger会在root的Logger的appender里输出。
   > 
   > 若是additivity设为false，则子Logger只会在自己的appender里输出，不会在root的logger的appender里输出。
   > 
   > 若是additivity设为true，则子Logger不止会在自己的appender里输出，还会在root的logger的appender里输出。
7. root
   > 根logger，也是一种logger，且只有一个level属性，作用是收集下面所有反馈上来的信息流并根据配置在root中appender进行输出。
8. springProfile：多环境
9. filter 子标签
   > 在简介中提到了filter；作用就是上面说的。可以为appender 添加一个或多个过滤器，可以用任意条件对日志进行过滤。appender 有多个过滤器时，按照配置顺序执行。
   * ThresholdFilter：临界值过滤器，过滤掉低于指定临界值的日志。当日志级别等于或高于临界值时，过滤器返回NEUTRAL；当日志级别低于临界值时，日志会被拒绝。
   * LevelFilter： 级别过滤器，根据日志级别进行过滤。如果日志级别等于配置级别，过滤器会根据`onMath`(用于配置符合过滤条件的操作) 和 `onMismatch`(用于配置不符合过滤条件的操作)接收或拒绝日志。

### MDC自定义参数
MDC自定义参数，一般用于在一个请求到达服务端之后，在当前线程执行的整个过程中，任何日志的打印，均会通过日志的格式化，默认打印设置的参数。在logback里除了默认的自定义参数之外，还可以设置自定义的字段。比如用户id，请求id等，方便业务定位问题时，能够高效的进行日志的筛选。

用`%X{key}`来作标识符进行输出，`key为`MDC设置的字段名称。

在实际的使用场景中，往往是一进入服务端，就设置相关的参数，那么后续的日志打印时，日志的格式化里面就回有对应的值，因此一般是通过`Filter`或者`Interceptor`进行实现。本示例使用第二种方式。
1. 编写拦截器，拦截方法进入时通过`MDC`进行相关参数的设置
2. 编写拦截器，因为http请求在容器层面依然是通过线程池进行管理的，为了避免不同的请求，使用的参数存在并发问题，因此在拦截方法执行完成之后，需要将`MDC`里的值进行清空。（MDC底层实现依然是`ThreadLocal`）
示例：`org.moon.config.LogBackOptimizeInterceptor`
3. 如果出现当前http请求过程中，出现异步线程处理时（子线程），则会出现异步线程中（子线程）丢失当前线程动态参数。
   > 工作线程不能总是从发起线程继承映射的诊断上下文的副本。这就是java.util.concurrent. executor用于线程管理时的情况。
   > 例如，newCachedThreadPool方法创建了一个ThreadPoolExecutor，和其他线程池代码一样，它有复杂的线程创建逻辑。
   > 在这种情况下，建议在将任务提交给执行器之前在原始(主)线程上调用MDC.getCopyOfContextMap()。
   > 当任务运行时，作为它的第一个操作，它应该调用MDC. setcontextmapvalues()来将原始MDC值的存储副本与新的Executor托管线程关联起来。
   > 
   > 在springboot下，使用TaskExecutorBuilder(ThreadPoolTaskExecutor)创建线程池，调用taskDecorator方法，拷贝MDC上下文：org.moon.config.ContextCopyingDecorator。
   > 如果是原生线程池，则可以利用如上思想，进行线程池的包装，线程执行前后做好MDC的拷贝和清除。



### 分层打印
核心为，利用aop，将接口的出参入参进行统一输出，通过aop配置拦截规则，从而实现无侵入式（隐式）打印出入参。避免业务代码里日志类代码的冗余。
1. 引入aop依赖
   ```
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-aop</artifactId>
   </dependency>
   ```
2. 配置aop拦截规则，比如拦截所有`Controller`以及`Service`的`public`方法（如果有其他层，以此类推）。
   ```
   execution(public * org.moon..*Controller.*(..))
   execution(public * org.moon..*ServiceImpl.*(..))
   ```
3. 明确拦截的事件，应该为`around`环绕通知，在其中实现参数的打印输出。如果执行有非日志处理类的异常，不做任何处理，接着往上抛，避免此处被吞掉，而导致上层的处理无法被执行。
示例：`org.moon.config.RunTimeLogAspect`

### 数据脱敏

### 审计日志

### 动态日志
#### Spring Boot Actuator
1. 添加依赖
   ```
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-actuator</artifactId>
   </dependency>
   ```
2. 配置`loggers`的`endpoint`
   ```
   # 开启全部endpoint
   management:
     endpoints:
       web:
         exposure:
           include: "*"
   
   # 只开启logger和健康检查    
   management:
     endpoints:
       web:
         exposure:
           include: loggers,health
   ```
3. 测试步骤：使用postman导入`postman/log.postman_collection.json`，测试顺序为`001 -> 002 -> 003 -> 004 -> 005 -> 003 -> 004`。默认`DEBUG`，通过005设置为`INFO`。
4. 测试步骤解析：
   1. `GET http://localhost/actuator`: 返回放开的`endpoint`
   2. `GET http://localhost/actuator/loggers`: 返回当前应用全部的日志级别信息
   3. `GET http://localhost/actuator/loggers/{name}`: 返回{name}的日志级别，也就是`LoggerFactory.getLogger`的name，满足xpath规则。
   4. `POST http://localhost/actuator/loggers/{name}`: 配置参数
      ```
      {
          "configuredLevel": "INFO",
          "effectiveLevel": "INFO"
      }
      ```
5. 实现原理（Spring Boot Actuator Log）

从依赖`spring-boot-actuator`中找到我们的`LoggersEndpoint`（所有的Actuator都是这一个路数）：`spring-boot-actuator-2.6.4.jar`--`org.springframework.boot.actuate.logging`。

在在每个`actuator Endpoint`的背后，必然还会存在一个`xxxEndpointAutoConfiguration`来为我们进行`Endpoint`的加载。而这些加载机制就都存放在`spring-boot-actuator-autoconfigure`中，
我们在其中可以找到`LoggersEndpointAutoConfiguration`用于加载`LoggersEndpoint`的配置类：`spring-boot-actuator-autoconfigure-2.6.4.jar`--`org.springframework.boot.actuate.autoconfigure.logging.LoggersEndpointAutoConfiguration`。
核心代码：
```java
@Bean
@ConditionalOnBean(LoggingSystem.class)
@Conditional(OnEnabledLoggingSystemCondition.class)
@ConditionalOnMissingBean
public LoggersEndpoint loggersEndpoint(LoggingSystem loggingSystem,
        ObjectProvider<LoggerGroups> springBootLoggerGroups) {
    return new LoggersEndpoint(loggingSystem, springBootLoggerGroups.getIfAvailable(LoggerGroups::new));
}
```
- LoggingSystem：一个抽象顶级类
- springBootLoggerGroups：存储了当前日志分组数据

总结：
1. 我们依赖了spring-boot-starter-actuator包后，里面依赖了spring-boot-actuator-autoconfigure 
2. 在启动扫描到spring-boot-actuator-autoconfigure 下的META-INF/spring.factories 时，LoggersEndpointAutoConfiguration会被加载到 
3. LoggersEndpointAutoConfiguration 内又声明了LoggersEndpoin 并赋值LoggingSystem 和springBootLoggerGroups 作为其参数
4. 项目启动后我们通过LoggersEndpoint 接口进行日志数据访问


#### 分布式配置中心
1. logback配置文件自动扫描：`<configuration scan="true" scanPeriod="15 seconds">`
2. 远程配置中心，如Apollo实现级别动态修改（arthas 动态修改）
```java
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
 
import javax.annotation.PostConstruct;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.List;
 
@Component
public class LogBackConfigLoader {
    /**
     * logger:日志对象.
     * @since JDK 1.7
     */
    private static Logger logger = LoggerFactory.getLogger(LogBackConfigLoader.class);
 
    @Value("${app.id}")
    private String appId;
 
    private void load (String externalConfigFileLocation) throws IOException, JoranException{
    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
 
    File externalConfigFile = new File(externalConfigFileLocation);
    if(!externalConfigFile.exists()){
        throw new IOException("Logback配置文件不存在");
    }else{
        if(!externalConfigFile.isFile()){
            throw new IOException("Logback配置文件不正确");
        }else{
            if(!externalConfigFile.canRead()){
                throw new IOException("Logback配置文件不能被读取");
            }else{
                JoranConfigurator configurator = new JoranConfigurator();
                configurator.setContext(lc);
                lc.reset();
                configurator.doConfigure(externalConfigFileLocation);
                StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
            }
        }
    }
}
 
    @PostConstruct
    private void  initLog(){
        try {
            String pathName = getPathName();
            String xmlPath = prop2Xml(pathName);
            load(xmlPath);
        }catch (Exception e){
            logger.warn("获取apollo日志logback.xml配置失败，logback使用默认配置！  原因:"+e.getMessage());
        }
    }
 
    @ApolloConfigChangeListener("logback.xml")
    private void anotherOnChange(ConfigChangeEvent changeEvent) {
        //当logback.xml文件改变的时候动态更新
        try {
            String pathName = getPathName();
            String xmlPath = prop2Xml(pathName);
            load(xmlPath);
        }catch (Exception e){
            logger.warn("apollo日志logback.xml配置热更新失败！  原因:"+e.getMessage());
        }
    }
 
    private String getPathName(){
        String pathName;
        String system = System.getProperty("os.name");
        if(system.toLowerCase().startsWith("win")){
            pathName = "C:/opt/data/"+appId+"/config-cache/";
        }else {
            //除了win其他系统路径一样
            pathName = "/opt/data/"+appId+"/config-cache/";
        }
 
        String cluster = "";
        List<String> inputArgs = ManagementFactory.getRuntimeMXBean().getInputArguments();
        for (String in : inputArgs){
            if(in.contains("Dapollo") && in.contains("cluster")){
                String[] clusters = in.split("=");
                cluster = clusters[1].replaceAll(" ","");
            }
        }
        pathName += appId+"+"+cluster+"+"+"logback.xml.properties";
        return pathName;
    }
 
    private String prop2Xml(String path) throws Exception{
        StringBuffer fileContent = new StringBuffer();
        File filename = new File(path);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        int f = 0;
        String line = "";
        line = br.readLine();
        while(line != null) {
            if(f>1){
                //前两行注释不要
                fileContent.append(line);
            }
            line = br.readLine();
            f++;
        }
 
        //去掉content=
        fileContent.replace(0,8,"");
        //java反转义
        String outContent = StringEscapeUtils.unescapeJava(fileContent.toString());
 
        //生成xml文件
        String outPath = path.replaceAll(".properties","");
        File file=new File(outPath);
        if(!file.exists()){
            file.createNewFile();
        }else {
            //先删除再重新创建不然会报错
            file.delete();
            file.createNewFile();
        }
        FileOutputStream out=new FileOutputStream(file,true);
        out.write(outContent.getBytes("utf-8"));
        out.close();
        return outPath;
    }
}
```

### 自定义注解
#### 自定义需要打印的异常
1. 自定义日志注解，设计需要打印的异常配置字段，可配置多异常
   - 自定义注解：`org.moon.annotation.MoonExceptionLog`
2. AOP拦截，判断配置异常是否与实际发生一致，进行日志打印或者其他策略进行处理
   - aop切点：`org.moon.config.RunTimeLogAspect.exceptionLog`
   - aop拦截具体异常：`org.moon.config.RunTimeLogAspect.afterThrowingRuntimeException`
   - aop拦截最大的异常：`org.moon.config.RunTimeLogAspect.afterThrowing`

### best practise
1. 日志格式统一化，可通过`MDC`丰富日志模板的内容，比如用户id，请求线程的流水号等（多线程需要进行上下文必要参数的传递）。
2. 日志分类：接口业务参数类日志，监控类（耗时，成功率）日志，审计日志等区分文件，方便不同维度的数据监控与展示。
3. logger的name，往往可以采用类路径的方式，因为其支持xpath进行匹配。越精准的类路径名称的logger放在前面，越模糊的放在后面进行兜底，最终没有匹配上的进入root。
4. 采用动态配置方式，将配置放到**分布式配置中心**中，可以实时调整配置。比如大促时，只保留核心接口的日志，非核心日志需要进行降级比如只保留warn和error日志。
5. 日志异步
6. 不同环境使用不同日志路径，通过环境区分不同配置，logback配置文件通过`springProperty`进行使用即可。示例中演示了不通的OS使用不同的配置路径。

### 参考
1. [看完这个不会配置 logback ，请你吃瓜！](https://juejin.cn/post/6844903641535479821)
2. [springboot+logback 日志输出企业实践（上）](http://t.cn/AigXlD6Q)
3. [Spring boot+LogBack+MDC实现链路追踪](https://juejin.cn/post/7074461710030995492)
4. [Spring Boot 系列：日志动态配置详解](https://zhuanlan.zhihu.com/p/266268191)