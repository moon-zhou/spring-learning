## spring boot 相关配置

### ApplicationRunner
ApplicationRunner执行的时机是：spring容器启动完成之后，就会紧接着执行这个接口实现类的run方法。使用方法如下：
1. 实现`ApplicationRunner`接口
2. 添加`@Component`加入spring上下文
3. 可以有多个接口实现，如果有顺序要求，可以通过`@Order`来进行控制