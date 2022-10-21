## spring boot transaction

### 使用方法
#### 声明式事务
通过在方法上使用`@Transactional`注解进行事务管理的操作叫声明式事务。

#### 编程式事务
**基于底层的API，开发者在代码中手动的管理事务的开启、提交、回滚等操作**。在spring项目中可以使用`TransactionTemplate`类的对象，手动控制事务。



### 失效场景
1. `@Transactional` 应用在非 `public` 修饰的方法上
1. `@Transactional` 注解属性 `propagation` 设置错误
1. `@Transactional` 注解属性 `rollbackFor` 设置错误
1. 同一个类中方法调用，导致`@Transactional`失效
1. 异常被`catch`捕获导致`@Transactional`失效


### 注意点
#### 长事务问题
##### 问题描述
当 `Spring` 遇到该注解时，会自动从数据库连接池中获取 connection，并开启事务然后绑定到 `ThreadLocal` 上，对于`@Transactional`注解包裹的整个方法都是使用同一个connection连接。
如果我们出现了耗时的操作，比如**第三方接口调用**，**业务逻辑复杂**，**大批量数据处理**等就会导致我们我们占用这个connection的时间会很长，数据库连接一直被占用不释放。一旦类似操作过多，就会导致数据库连接池耗尽。

##### 引发问题
1. 数据库连接池被占满，应用无法获取连接资源；
2. 容易引发数据库死锁；
3. 数据库回滚时间长；
4. 在主从架构中会导致主从延时变大。

##### 解决方案
解决长事务的宗旨就是**对事务方法进行拆分，尽量让事务变小，变快，减小事务的颗粒度**。



### 参考
1. [Spring中的事务Transactional，这样用真的对么？](https://juejin.cn/post/7033036488766193678)