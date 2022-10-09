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

在实际的使用场景中，往往是一进入服务端，就设置相关的参数，那么后续的日志打印时，日志的格式化里面就回有对应的值，因此一般是通过`Filter`或者`Interceptor`进行实现。本示例使用第二种方式。
1. 编写拦截器，拦截方法进入时通过`MDC`进行相关参数的设置
2. 编写拦截器，因为http请求在容器层面依然是通过线程池进行管理的，为了避免不同的请求，使用的参数存在并发问题，因此在拦截方法执行完成之后，需要将`MDC`里的值进行清空。（MDC底层实现依然是`ThreadLocal`）
示例：`org.moon.config.LogBackOptimizeInterceptor`


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


### best practise
1. logger的name，往往可以采用类路径的方式，因为其支持xpath进行匹配。越精准的类路径名称的logger放在前面，越模糊的放在后面进行兜底，最终没有匹配上的进入root。
2. 采用动态配置方式，将配置放到**分布式配置中心**中，可以实时调整配置。比如大促时，只保留核心接口的日志，非核心日志需要进行降级比如只保留warn和error日志。

### 参考
1. [看完这个不会配置 logback ，请你吃瓜！](https://juejin.cn/post/6844903641535479821)