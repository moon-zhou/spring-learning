### SpringBoot Logback使用

#### 日志打印规则
1. 按日志类别分
    * 业务日志（按业务分文件打印）
    * 监控日志（拦截器分层打印-controller/services/rpc）
2. 按日志格式（总体有个格式-项目名、部署集群名、IP、流水号、用户id、sessionid、类名，此处为内容隐位格式）
    * 超长日志隐位/丢弃
    * 敏感信息脱敏

#### 主要类容
1. MDC自定义参数
2. 分层打印
3. 数据脱敏
1. 审计日志

#### 使用springboot配置
1. 配置日志级别
    ```
    logging:
      level:
       root: info
    ```

#### Logback使用
1. 核心配置结构
    ```
    configuration
    ├─contextName
    ├─property
    ├─appender
    ├─logger
    └─root
    ``` 
1. configuration配置(属性如下)
    * debug：默认为false，若设置为true，则打印出logback内部日志信息
    * scan：默认值为true，若设置为true，配置文件如果发生改变，将会被重新加载。
    * 与scan配合使用，当scan为true时，此属性生效，默认的时间间隔为1分钟，设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。如可以设置为`scanPeriod="30 seconds"`每30秒检测一次。
    ```java
    <configuration scan="true" scanPeriod="1 seconds">
    ```
1. contextName：日志名，可以使用`%contextName`来引用
1. appender
    * ConsoleAppender(输出到控制台)
    * FileAppender(输出到文件)
    * RollingFileAppender(先将日志输出到指定文件，满足一定条件后将日志记录到其他文件中)
    
#### appender
appender是定义日志输出的策略
1. ConsoleAppender
