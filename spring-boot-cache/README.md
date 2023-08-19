# spring cache


## 介绍
`Spring Cache` 是 Spring 提供的一整套的缓存解决方案。虽然它本身并没有提供缓存的实现，但是它提供了一整套的接口和代码规范、配置、注解等，这样它就可以整合各种缓存方案了，
比如 Redis、Ehcache，我们也就不用关心操作缓存的细节。其核心注解有 `@CacheConfig`，`@Cacheable`，`@CachePut`，`@CacheEvict`。
