## Redis with SpringBoot

### 背景
通常系统里，redis中间件多用于缓存进行使用，以减轻数据库的压力。

分布式流水号
数据库也可以，但是并发度不高，同时也没有Redis灵活。需要设计表结构，DDL。

### RedisTemplate


### Redisson


#### Redisson和Jedis、Lettuce有什么区别？
Redisson是更高层的抽象，Jedis和Lettuce是Redis命令的封装。

Jedis是Redis官方推出的用于通过Java连接Redis客户端的一个工具包，提供了Redis的各种命令支持
Lettuce是一种可扩展的线程安全的 Redis 客户端，通讯框架基于Netty，支持高级的 Redis 特性，比如哨兵，集群，管道，自动重新连接和Redis数据模型。 Spring Boot 2.x 开始 Lettuce 已取代 Jedis 成为首选 Redis 的客户端。
Redisson是架设在Redis基础上，通讯基于Netty的综合的、新型的中间件，企业级开发中使用Redis的最佳范本

Jedis把Redis命令封装好，Lettuce则进一步有了更丰富的Api，也支持集群等模式。但是两者也都点到为止，只给了你操作Redis数据库的脚手架，而Redisson则是基于Redis、Lua和Netty建立起了成熟的分布式解决方案，甚至redis官方都推荐的一种工具集。


### 参考
1. [最强分布式工具Redisson（一）：分布式锁](https://juejin.cn/post/6961380552519712798)