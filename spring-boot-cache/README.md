# spring cache

## 介绍

`Spring Cache` 是 Spring 提供的一整套的缓存解决方案。虽然它本身并没有提供缓存的实现，但是它提供了一整套的接口和代码规范、配置、注解等，这样它就可以整合各种缓存方案了，
比如 Redis、Ehcache，我们也就不用关心操作缓存的细节。其核心注解有 `@CacheConfig`，`@Cacheable`，`@CachePut`，`@CacheEvict`。

## 使用

### 引入依赖
```
<!-- Spring Cache -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>

<!-- redis -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<!-- 用于redis lettuce 连接池pool使用 -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
</dependency>
```

### 启用缓存
```
@EnableCaching
```
有一个缓存管理器Bean，默认情况下，@EnableCaching 将注册一个ConcurrentMapCacheManager的Bean，不需要单独的 bean 声明。
ConcurrentMapCacheManager将值存储在ConcurrentHashMap的实例中，这是缓存机制的最简单的线程安全实现。

### 自定义缓存管理器
```
org.moonzhou.springcache.config.RedisConfig
```
主要是处理redis序列化，使用json格式，方便直接查看。但是配置自定义缓存管理器之后，缓存过期配置就会失效(`spring.cache.redis.time-to-live`)，导致其永久生效。
因此在自定义缓存管理器里需要加上缓存过期的配置(以及其他配置，可以参考器默认的管理器的实现：`RedisCacheConfiguration`，加载配置`CacheProperties`)。

### 注解使用
1. `@Cacheable`: 触发将数据保存到缓存的操作。
   - 如果缓存中有数据，被标注的方法不调用。
   - key 默认自动生成：缓存名字::SimpleKey [] ，如 BizService::SimpleKey [] 。
   - 缓存值默认使用 jdk 序列化机制将序列化后的数据存到缓存。
   - 默认过期时间是 -1（不过期）。
   - 缓存的数据为方法的返回值，需要实现`Serializable`。
   - Condition & Unless
     - condition作用：指定缓存的条件（满足什么条件才缓存），可用 SpEL 表达式（如 #id>0，表示当入参 id 大于 0 时才缓存）
     - unless作用 : 否定缓存，即满足 unless 指定的条件时，方法的结果不进行缓存，使用 unless 时可以在调用的方法获取到结果之后再进行判断（如 #result == null，表示如果结果为 null 时不缓存）
2. `@CacheEvict`: 将数据从缓存删除，其他属性`allEntries`、`beforeInvocation`。
3. `@CachePut`: 沉浸式更新缓存。
4. `@Caching`: 组合多个缓存操作。
   ```
   @Caching(
        put = {
            @CachePut(value = "myCache", key = "#result.id"),
            @CachePut(value = "myOtherCache", key = "#result.id"),
            @CachePut(value = "myThirdCache", key = "#result.name")
        },
        evict = {
            @CacheEvict(value = "otherCache", key = "#id")
        },
        cacheable = {
            @Cacheable(value = "myFourthCache", key = "#id"),
            @Cacheable(value = "myFifthCache", key = "#result.id")
        }
    )
   ```
5. `@CacheConfig`: 可以将一些缓存配置简化到类级别的一个地方，这样我们就不必多次声明相关值。


### SpEL上下文数据
Spring Cache提供了一些供我们使用的SpEL上下文数据，下表直接摘自Spring官方文档：

| **名字**        | **位置**   | **描述**                                                                                              | **示例**               |
| --------------- | ---------- | ----------------------------------------------------------------------------------------------------- | ---------------------- |
| methodName      | root对象   | 当前被调用的方法名                                                                                    | `#root.methodName`     |
| method          | root对象   | 当前被调用的方法                                                                                      | `#root.method.name`    |
| target          | root对象   | 当前被调用的目标对象                                                                                  | `#root.target`         |
| targetClass     | root对象   | 当前被调用的目标对象类                                                                                | `#root.targetClass`    |
| args            | root对象   | 当前被调用的方法的参数列表                                                                            | `#root.args[0]`        |
| caches          | root对象   | 当前方法调用使用的缓存列表（如@Cacheable(value={"cache1", "cache2"})），则有两个cache                 | `#root.caches[0].name` |
| *argument name* | 执行上下文 | 当前被调用的方法的参数，如findById(Long id)，我们可以通过#id拿到参数                                  | `#user.id`             |
| result          | 执行上下文 | 方法执行后的返回值（仅当方法执行之后的判断有效，如‘unless’，'cache evict'的beforeInvocation=false） | `#result`              |

其他关于 Cache 详细配置或注解，请参考文章[基于Redis的Spring cache 缓存介绍](https://www.cnblogs.com/junzi2099/p/8301796.html)或spring官方文档

## 原理
和 spring 的事务管理类似，spring cache 的关键原理就是 spring AOP，通过 spring AOP，其实现了在方法调用前、调用后获取方法的入参和返回值，进而实现了缓存的逻辑。


### 核心源码
```
// 默认管理器
org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration --> org.springframework.data.redis.cache.RedisCacheManager

// 配置
org.springframework.boot.autoconfigure.cache.CacheProperties

// AOP
org.springframework.cache.interceptor.CacheInterceptor

org.springframework.cache.annotation.AnnotationCacheOperationSource
org.springframework.cache.annotation.SpringCacheAnnotationParser
```



## 缺陷
1. 读多写少、即时性与一致性要求不高的数据要求场景下，可以使用 Spring Cache。
2. 在双写模式或者失效模式下，可能会出现缓存数据一致性问题（读取到脏数据），Spring Cache 暂时没办法解决。(多线程操作公共资源时，会有并发安全问题，需要考虑特殊的设计)

## 参考
1. [spring cache 官方](https://docs.spring.io/spring-framework/reference/integration/cache.html)
2. [spring redis 官方](https://docs.spring.io/spring-data/redis/docs)
3. [SpringBoot项目中使用缓存Cache的正确姿势！！!](https://juejin.cn/post/7220292698854752313)
4. [优雅的缓存解决方案--SpringCache和Redis集成(SpringBoot)](https://juejin.cn/post/6844903807646711821)