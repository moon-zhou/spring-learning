### spring AOP

Aop（Aspect Oriented Programming），面向切面编程

面向切面编程，就是在程序运行时，不改变程序源码的情况下，动态的增强方法的功能，常见的使用场景非常多：
1. 日志
1. 事务
....

相关概念：

| 概念         | 说明                 |
| ------------ | -------------------- |
| 切点         | 要添加代码的地方     |
| 通知（增强） | 向切点动态添加的代码 |
| 切面         | 切点+通知            |
| 连接点       | 切点的定义           |

Java 中的动态代理有两种实现方式：
- cglib
- jdk

其中jdk动态代理必须是基于接口进行代理，也就是我们的目标类必须实现一个接口，才能进行代理。

五种通知:
- 前置通知(before)
- 后置通知(after)
- 异常通知(afterThrowing)
- 返回通知(afterReturning)
- 环绕通知(around)

执行顺序：
> around > before > around > after > afterReturning

切点定义：
- 使用自定义注解
- 使用规则

#### 自定义注解
1. 侵入式的
1. 方法级注解

#### 规则
execution表达式

#### JDBC Template
JdbcTemplate 是 Spring 利用 Aop 思想封装的 JDBC 操作工具。

在查询时，如果使用了 BeanPropertyRowMapper，要求查出来的字段必须和 Bean 的属性名一一对应。如果不一样，则不要使用 BeanPropertyRowMapper，此时需要自定义 RowMapper 或者给查询的字段取别名。


#### 事务
Spring 中的事务主要是利用 Aop 思想，简化事务的配置，可以通过 Java 配置也可以通过 XML 配置。


#### My Hub
```
动态代理示例：
https://github.com/moon-zhou/advanced-java/tree/master/advancedprogramming/src/main/java/org/moonzhou/proxy


org.moonzhou.spring.aop.annotationconfig.aspect.MoonLogAspectV1
和
org.moonzhou.spring.aop.annotationconfig.aspect.MoonLogAspectV2
运行时只能放开已开，避免同时放开输出信息错乱。
但是同时放开，也可以看出多个aop时，是责任链模式，类似filter的特性，先开始的后结束。
```


#### 参考
1. [Spring 学习，看这一篇万余字干货就够了（下）](https://zhuanlan.zhihu.com/p/99183453)