## spring boot transaction

### 使用方法
#### 声明式事务
通过在方法上使用`@Transactional`注解进行事务管理的操作叫声明式事务。
注解配置在类以及类方法上，配置在接口上是无效的。
- 作用于类：当把 `@Transactional` 注解放在类上时，表示所有该类的`public`方法都配置相同的事务属性信息。
- 作用于方法：当类配置了`@Transactional`，方法也配置了`@Transactional`，方法的事务会覆盖类的事务配置信息。

属性：
- propagation：默认值`Propagation.REQUIRED`
  - Propagation.REQUIRED：如果当前存在事务，则加入该事务，如果当前不存在事务，则创建一个新的事务。( 也就是说如果A方法和B方法都添加了注解，在默认传播模式下，A方法内部调用B方法，会把两个方法的事务合并为一个事务 ）
  - Propagation.SUPPORTS：如果当前存在事务，则加入该事务；如果当前不存在事务，则以非事务的方式继续运行。
  - Propagation.MANDATORY：如果当前存在事务，则加入该事务；如果当前不存在事务，则抛出异常。
  - Propagation.REQUIRES_NEW：重新创建一个新的事务，如果当前存在事务，暂停当前的事务。( 当类A中的 a 方法用默认Propagation.REQUIRED模式，类B中的 b方法加上采用 Propagation.REQUIRES_NEW模式，然后在 a 方法中调用 b方法操作数据库，然而 a方法抛出异常后，b方法并没有进行回滚，因为Propagation.REQUIRES_NEW会暂停 a方法的事务 )
  - Propagation.NOT_SUPPORTED：以非事务的方式运行，如果当前存在事务，暂停当前的事务。
  - Propagation.NEVER：以非事务的方式运行，如果当前存在事务，则抛出异常。
  - Propagation.NESTED ：和 Propagation.REQUIRED 效果一样。
- isolation
  - Isolation.DEFAULT：使用底层数据库默认的隔离级别。
  - Isolation.READ_UNCOMMITTED
  - Isolation.READ_COMMITTED
  - Isolation.REPEATABLE_READ
  - Isolation.SERIALIZABLE
- timeout：事务的超时时间，默认值为 -1。如果超过该时间限制但事务还没有完成，则自动回滚事务。（避免**长事务**）
- readOnly：指定事务是否为只读事务，默认值为 `false`；为了忽略那些不需要事务的方法，比如读取数据，可以设置 `read-only` 为 `true`。
- rollbackFor：用于指定能够触发事务回滚的异常类型，可以指定多个异常类型。
- noRollbackFor：抛出指定的异常类型，不回滚事务，也可以指定多个异常类型。

使用细节：
1. 如果没有注解，则发生没有处理的运行时异常时，已经执行的sql，则会生效，不会回滚。之后的sql则因为异常已经发生，不会执行。整体的sql不会回滚。
2. 添加了注解，不指定`rollbackFor`，则对于抛出`RuntimeException`，即使已经执行的sql，也会进行回滚。如果指定具体的`rollbackFor`异常，抛出异常不匹配，则执行的sql是不会回滚的，**当然，抛出的异常是 `rollbackFor` 指定的异常的子类，事务同样会回滚**。实际一般配置为`@Transactional(rollbackFor = {Exception.class})`
3. 注解只能在 `public` 的方法上。
4. 入口方法没有添加事务注解，调用了内部添加了事务注解的公共方法，则被调用方法事务注解不生效。**如果入口方法本身是带有事务注解的公共方法，再调用另一个有事务注解的公共方法，事务注解会生效，此处遵循事务传播机制。**


#### 编程式事务
**基于底层的API，开发者在代码中手动的管理事务的开启、提交、回滚等操作**。在spring项目中可以使用`TransactionTemplate`类的对象，手动控制事务。
```
private final TransactionTemplate transactionTemplate;

// 带返回值
transactionTemplate.execute(status -> {
  try {
    // biz code
    return XXX;
  } catch (Exception e) {
  
    // rollback
    status.setRollbackOnly();
    return XXX;
  }
});

// 无返回值
transactionTemplate.executeWithoutResult(status -> {
  try {
    // biz code
  } catch (Exception e) {
  
    // rollback
    status.setRollbackOnly();
  }
});
```


### 失效场景
1. `@Transactional` 应用在接口的方法上（==>应当配置在实现类的方法上）
2. `@Transactional` 应用在非 `public` 修饰的方法上
3. `@Transactional` 注解属性 `propagation` 设置错误
4. `@Transactional` 注解属性 `rollbackFor` 设置错误
5. 同一个类中方法调用，导致`@Transactional`失效（事务的管理是通过代理执行的方式生效的，如果是方法内部调用，将不会走代理逻辑，也就调用不到了。）
6. 异常被`catch`捕获导致`@Transactional`失效
7. 数据库引擎不支持事务，常用的`MySQL`数据库默认使用支持事务的`innodb`引擎。一旦数据库引擎切换成不支持事务的`myisam`，那事务就从根本上失效了。


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

#### 多线程事务问题

### 参考
1. [Spring中的事务Transactional，这样用真的对么？](https://juejin.cn/post/7033036488766193678)
2. [一口气说出 6种，@Transactional注解的失效场景](https://juejin.cn/post/6844904096747503629)