## springboot-mybatis-plus

springboot integrate with mybatis-plus

### CRUD接口
mybatis-plus封装了一些最基础的CRUD方法，只需要直接继承mybatis-plus提供的接口，无需编写任何SQL，即可使用。

### Mapper CRUD接口
`com.baomidou.mybatisplus.core.mapper.BaseMapper`

### Service CRUD 接口
```
com.baomidou.mybatisplus.extension.service.IService
com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
```

### 条件构造器

### 字段加密
核心思路：实现加密的`BaseTypeHandler`
1. 编写AES加密方法，秘钥可配置（后续可扩张加密方法RAS等）
2. 实现`BaseTypeHandler`的相关接口，相关接口里包含入库和查询，调用对应的加密和解密方法
3. 后期可以扩展不同的加密方式，通过配置自定义
整体的设计思路，与传输层加密类似，传输层加密是去继承`HttpServletRequestWrapper`,重写里面获取参数的方法（`OncePerRequestFilter`）。

### 参考
1. [SpringBoot整合mybatis-plus--入门超详细](https://www.jianshu.com/p/28d6d9a56b62)
2. [MyBatis-Plus](https://baomidou.com/)
3. [awesome-mybatis-plus](https://github.com/baomidou/awesome-mybatis-plus)
4. [mybatis-plus-doc](https://github.com/baomidou/mybatis-plus-doc)
5. [MyBatis-Plus + SpringBoot实现简单权限管理](https://www.imooc.com/learn/1294)
6. [基于Mybatis-Plus的字段加密方案](https://juejin.cn/post/7076350146660794381)
7. [mybatis](https://mybatis.org/mybatis-3/zh/configuration.html#typeHandlers)