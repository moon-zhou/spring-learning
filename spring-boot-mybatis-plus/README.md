## springboot-mybatis-plus

springboot integrate with mybatis-plus

### CRUD接口
mybatis-plus封装了一些最基础的CRUD方法，只需要直接继承mybatis-plus提供的接口，无需编写任何SQL，即可使用。

### Mapper CRUD接口
1. `com.baomidou.mybatisplus.core.mapper.BaseMapper`
2. 使用`@MapperScan`或者`@Mapper`

### Service CRUD 接口
```
com.baomidou.mybatisplus.extension.service.IService
com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
```

### 条件构造器
TODO

### 字段加密
核心思路：实现加密的`BaseTypeHandler`
1. 编写AES加密方法，秘钥可配置（后续可扩张加密方法RAS等）
2. 实现`BaseTypeHandler`的相关接口，相关接口里包含入库和查询，调用对应的加密和解密方法
3. 后期可以扩展不同的加密方式，通过配置自定义
整体的设计思路，与传输层加密类似，传输层加密是去继承`HttpServletRequestWrapper`,重写里面获取参数的方法（`OncePerRequestFilter`）。


### 乐观锁 & 悲观锁
#### 乐观锁
乐观锁往往不需要通过数据库的锁机制，乐观锁更多的是一种通用的设计思想。在mybatis-plus里的体现，是持久层（大方向上还是属于业务层面）的数据防止并发，数据一致性的解决方案。 适用场景就是乐观锁的通用场景，读多写少。

乐观锁假设数据一般情况下不会造成冲突，所以在数据进行提交更新的时候，才会正式对数据的冲突与否进行检测，如果发现冲突了，则让返回用户错误的信息，让用户决定如何去做。
所以乐观锁的核心就是：**冲突检测**和**数据更新**，是一种典型的**Compare and Swap(CAS)技术**。

对于CAS，典型的就是ABA问题，所以可以在业务通过version顺序递增来进行避免。

具体实现：每次在执行数据的修改操作时，都会带上一个版本号，一旦版本号和数据的版本号一致就可以执行修改操作并对版本号执行+1操作，否则就执行失败。因为每次操作的版本号都会随之增加，所以不会出现ABA问题，因为版本号只会增加不会减少。
```
//查询出商品信息，version = 1
select version from items where id=1
//修改商品库存为2
update items set quantity=2,version = 3 where id=1 and version = 2;
```
以上SQL其实还是有一定的问题的，就是一旦高并发的时候，就只有一个线程可以修改成功，那么就会存在大量的失败。

对于像淘宝这样的电商网站，高并发是常有的事，总让用户感知到失败显然是不合理的。所以，还是要想办法减小乐观锁的粒度的。
有一条比较好的建议，可以减小乐观锁力度，最大程度的提升吞吐率，提高并发能力！如下：
```
//修改商品库存
update item
set quantity=quantity - 1
where id = 1 and quantity - 1 > 0
```

在 `mybatis-plus` 中的使用：
1. 数据库表创建`version`字段
2. `@Version`注解对应实体上
3. 高并发模拟测试
TODO


#### 悲观锁
具有强烈的独占和排他特性。它指的是对数据被外界（包括本系统当前的其他事务，以及来自外部系统的事务处理）修改持保守态度，因此，在整个数据处理过程中，将数据处于锁定状态。实现方式往往依靠数据库提供的锁机制。

悲观锁的实现方式有两种：共享锁(读锁)和排它锁(写锁)。
- 共享锁又称为读锁，简称S锁，顾名思义，共享锁就是多个事务对于同一数据可以共享一把锁，都能访问到数据，但是只能读不能修改。e.g.`sql后加LOCK IN SHARE MODE，比如SELECT ... LOCK IN SHARE MODE`
- 排他锁又称为写锁，简称X锁，顾名思义，排他锁就是不能与其他所并存，如一个事务获取了一个数据行的排他锁，其他事务就不能再获取该行的其他锁，包括共享锁和排他锁，但是获取排他锁的事务是可以对数据就行读取和修改。e.g.`sql后加FOR UPDATE，比如SELECT ... FOR UPDATE`

使用悲观锁时，我们必须关闭mysql数据库中自动提交的属性，命令set autocommit=0;即可关闭，因为MySQL默认使用autocommit模式，也就是说，当你执行一个更新操作后，MySQL会立刻将结果进行提交。
```
//0.开始事务
begin; 
//1.查询出商品库存信息
select quantity from items where id=1 for update;
//2.修改商品库存为2
update items set quantity=2 where id = 1;
//3.提交事务
commit;
```

### 自定义方法
1. 继承`BaseMapper`，定义自己的方法：`org.moonzhou.mybatisplus.dao.base.MyBaseMapper`
2. 继承`AbstractMethod`，实现`injectMappedStatement`方法，即自定义方法的sql拼接。
3. 继承`DefaultSqlInjector`，将实现的方法进行注入：`org.moonzhou.mybatisplus.method.MySqlInjector`
4. 将注入器交给`spring`管理（实例化bean）


### 日志
正常添加配置：`mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl`，但是打开该实现类，就回发现，里面的打印均是通过`System.err/System.out`来实现的。
所以如果真正上生产，在本身springboot的应用下，logback如果未配置输出控制台的日志到文件（通常不配置），此方法配置的日志就没有办法打印。

除了控制台输出的日志实现，还有`org.apache.ibatis.logging.slf4j.Slf4jImpl`等实现方式，具体可看源码：`org.apache.ibatis.logging`

最简单的方法就是模仿如上实现类，编写自己的日志处理类，直接将里面的`System.err/System.out`替换为`log`输出即可。e.g.`org.moonzhou.mybatisplus.logging.MySqlLogImpl`。
基本上实现之后，依赖父类项目里的`logback`统一配置。

关闭日志配置为：`mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.nologging.NoLoggingImpl`



### 分页
TODO


### 监控(TODO)
数据库连接线程池管理
#### Hikara
JMX

#### Druid

#### p6spy

### 参考
1. [SpringBoot整合mybatis-plus--入门超详细](https://www.jianshu.com/p/28d6d9a56b62)
2. [MyBatis-Plus](https://baomidou.com/)
3. [awesome-mybatis-plus](https://github.com/baomidou/awesome-mybatis-plus)
4. [mybatis-plus-doc](https://github.com/baomidou/mybatis-plus-doc)
5. [MyBatis-Plus + SpringBoot实现简单权限管理](https://www.imooc.com/learn/1294)
6. [基于Mybatis-Plus的字段加密方案](https://juejin.cn/post/7076350146660794381)
7. [mybatis](https://mybatis.org/mybatis-3/zh/configuration.html#typeHandlers)
8. [MyBatis-Plus 的进阶](https://juejin.cn/post/7028953797317623816)
9. [数据库中的乐观锁与悲观锁](https://www.cnblogs.com/kyoner/p/11318979.html)
10. [面试必备的数据库悲观锁与乐观锁](https://zhuanlan.zhihu.com/p/62663560)
11. [mybatis plus sql injector demo](https://gitee.com/baomidou/mybatis-plus-samples/tree/master/mybatis-plus-sample-deluxe)
12. [MyBatisPlus(SpringBoot版)--2022](https://www.cnblogs.com/manmanblogs/p/16041169.html)
13. [mybatis plus 看这篇就够了，一发入魂](https://juejin.cn/post/6961721367846715428)