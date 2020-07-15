### SpringBoot Logback使用

#### 日志打印规则
1. 按日志类别分
    * 业务日志（按业务分文件打印）
    * 监控日志（拦截器分层打印-controller/services/rpc）


#### 主要类容
1. MDC自定义参数
2. 分层打印
3. 数据脱敏

#### Logback使用
1. 核心配置结构
    ```
    configuration
    ├─property
    ├─appender
    ├─logger
    └─root
    ``` 
1. appender
    * ConsoleAppender(输出到控制台)
    * FileAppender(输出到文件)
    * RollingFileAppender(先将日志输出到指定文件，满足一定条件后将日志记录到其他文件中)