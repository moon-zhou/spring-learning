# spring-boot-j2cache

## 介绍
j2cache是OSChina(开源中国)目前正在使用的两级缓存框架。

j2cache的两级缓存结构：
- L1： 进程内缓存 caffeine/ehcache
- L2： 集中式缓存 Redis/Memcached

j2cache其实并不是在重复造轮子，而是作资源整合，即将Ehcache、Caffeine、redis、Spring Cache等进行整合。

数据读取
读取顺序 -> L1 -> L2 -> DB

数据更新
1. 从数据库中读取最新数据，依次更新 L1 -> L2 ，发送广播清除某个缓存信息
2. 接收到广播（手工清除缓存 & 一级缓存自动失效），从 L1 中清除指定的缓存信息








## 使用


## 参考
1. [J2Cache Java 两级缓存框架](https://www.oschina.net/p/j2cache?hmsr=aladdin1e1)
2. [gitee J2Cache](https://gitee.com/ld/J2Cache)
3. [【SpringBoot应用篇】SpringBoot集成j2cache二级缓存框架](https://www.codetd.com/article/14992501)
4. [springboot使用j2cache框架和aspectj自定义缓存](https://blog.51cto.com/u_15980129/6087406)
