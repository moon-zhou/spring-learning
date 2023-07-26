# spring session

## 背景
使用Java开发Web应用时，在单体应用时，使用HttpSession管理会话，HttpSession默认使用内存来管理Session。因此将应用横向扩展时，就会出现Session共享问题。针对这类问题，最原始的方案：
1. session复制（web容器实现，比如Tomcat支持 Session 集群，可在各 Tomcat 服务器间复制全部 session 信息），但也存在信息冗余，大对象复制慢等问题。
2. hash（sessionId）使其落到固定的机器上，问题是集群里的机器数目发生变化时，可能就会导致用户访问的机器发生变化，而变化的机器上没有相关session。

所以为了更符合分布式场景下的session管理，存储session的地方就不能时应用服务的内存，必须使用类似redis的独立存储中间件。同时实现将session在中间件中操作（继承重构`HttpServletRequestWrapper`,`HttpSessionWrapper`类,覆盖原来和session存取相关的方法）。
![standalone-distribute-session](./img/README-standalone-distribute-session.png)


## 是什么？
`Spring Session` 是 `spring` 旗下的一个项目，把 `servlet` 容器实现的 `httpSession` 替换为 `springSession`，专注于解决`Servlet HttpSession`管理问题。同时session存储支持多种方式，以实现分布式系统中依然使用。
![session-persistence-type](./img/README-session-persistence-type.png)

## 使用
### 引入依赖
```
<dependencies>
    <!--redis 依赖-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <!--sessions 依赖-->
    <dependency>
        <groupId>org.springframework.session</groupId>
        <artifactId>spring-session-data-redis</artifactId>
    </dependency>
</dependencies>
```

### 添加配置
```
server:
  port: 8080
spring:
  session: # 设置session存储类型
    store-type: redis
  redis: # 配置redis
    port: 6379
    host: localhost
    database: 0
```

### 添加注解
```
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800, redisNamespace = "moon:session")
```

### 序列化
Spring-Session 默认使用 JDK 序列化机制，要求类实现 Serializable 接口，序列化后是二进制字节数组，不易看懂。使用 JSON 序列化机制，序列化后的字符串容易看懂。(存储在redis中的值)
示例中直接使用了fastjson的json格式序列化方法。**当然，不配置序列化也可以，在某种程度上，默认的序列化方式也有一定的混淆作用，在数据安全上有一定的作用。**
```
org.moonzhou.springsession.config.SessionConfig
```
![session-serialize](./img/README-session-serialize.png)


### 测试
```
org.moonzhou.springsession.controller.SessionController
```

### 其他一些配置
```
# 设置 Spring Session 的过期时间。如果不指定单位模式是 s。 功能同 @EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800, redisNamespace = "moon:session")
spring.session.timeout=10m
# 存储 Session的命名空间(前缀)，默认是spring:session。
spring.session.redis.namespace=moon:session

# 将session 中的数据同步到redis 中提供了两种模式, 一种是当响应结束后同步, 另一种种是实时同步, 默认时第一种（on_save）.
# ON_SAVE: 只有当SessionRepository.save(Session)方法被调用时,才会将session中的数据同步到redis中. 在web 应用中, 当请求完成响应后, 才开始同步. 也就是说在执行response之前session数据都是缓存在本地的.(类似批量操作)
# IMMEDIATE: 实时同步session 数据到redis. 当执行SessionRepository.createSession()时, 会将session数据同步到redis中;当对session的attribute进行set/remove 等操作时, 也会同步session中的数据到redis中.
# 测试方法可以在操作session返回时（即controller方法return那一行），打上断点进行debug
spring.session.redis.flush-mode=on_save
```

## 底层实现
### 存储数据结构
```
> keys *
1) "moon:session:expirations:1690380180000"
2) "moon:session:sessions:21563c25-b4b6-4d8c-bbed-fb3226fee2c4"
3) "moon:session:sessions:expires:21563c25-b4b6-4d8c-bbed-fb3226fee2c4"

> smembers moon:session:expirations:1690380180000
1) "\"expires:21563c25-b4b6-4d8c-bbed-fb3226fee2c4\""

> hmget moon:session:sessions:21563c25-b4b6-4d8c-bbed-fb3226fee2c4 sessionAttr:user creationTime maxInactiveInterval lastAccessedTime
1) "{\"@type\":\"org.moonzhou.springsession.bean.User\",\"age\":18,\"name\":\"zhou\"}"
2) "1690378330301"
3) "1800"
4) "1690378330301"

> get moon:session:sessions:expires:21563c25-b4b6-4d8c-bbed-fb3226fee2c4
""
```

```
> MULTI
"OK"

> ttl moon:session:expirations:1690380180000
"QUEUED"

> ttl moon:session:sessions:21563c25-b4b6-4d8c-bbed-fb3226fee2c4
"QUEUED"

> ttl moon:session:sessions:expires:21563c25-b4b6-4d8c-bbed-fb3226fee2c4
"QUEUED"

> EXEC
1) "547"
2) "547"
3) "247"
```

1. 第一个key`moon:session:expirations:`：`Set` 类型的 Redis 数据结构，存储的值sessionId。key后面的时间戳，根据这个 Session 过期时刻滚动至后5分钟而计算得出。
2. 第一个key`moon:session:sessions:`：`Hash` 类型的 Redis 数据结构，存储值为session的信息，包括 Session 的最近访问时间（lastAccessedTime）、过期时间间隔（maxInactiveInterval，默认是 30 分钟，这里保存的秒值）、创建时间（creationTime）、sessionAttr 等等。
3. 第三个key`moon:session:sessions:expires:`：`String` 类型的 Redis 数据结构，不存储任何有用数据，只是为表示 Session 过期而设置。这个 key 在 Redis 中的过期时间即为 Session 的过期时间，即代码中配置的timeout。

### 缺陷
1. 无法做到 Session 的过期以及销毁事件的实时发布(redis的惰性删除，如果长时间无访问或者无操作spring session，session即使过期了也不会删除)
2. 序列化方式可能对一些特定类型的会话，支持的不是很好
3. Redis 存储一个会话需要 3 个 键值，占用空间稍微大些
4. 在高并发场景下，Session 因为不是 CAS(Compare And Set) 操作，所以可能存在一些并发问题（小问题）

## 参考
1. [SpringSession系列-集成SpringBoot](https://juejin.cn/post/6844903703921557517)
2. [Spring Session 的两种刷新模式-RedisFlushMode](https://blog.csdn.net/cristianoxm/article/details/120764550)
3. [SpringBoot 整合 Spring-Session 实现分布式会话（实战篇）](https://blog.csdn.net/piaoranyuji/article/details/125865704)