### spring boot offline
下线web应用下http接口的组件模块，使用springboot应用作为演示实例。

#### 核心设计思想
* aop切面，判断出当前请求已下线直接处理
* 下线请求可以动态配置，但是需要结合分布式配置中心（此示例不实现该特性）
* 表单请求：统一页面展示
* ajax请求：json格式的下线提示


#### 实现方式
1. annotation注解
2. execution表达式

#### 测试
1. 注解方式下线测试案例
```
服务未下线(均未配置注解)：
// 直接访问，返回字符串
http://localhost:8080/eonline/retStr

// 访问页面，动态参数回显
http://localhost:8080/eonline/retDynamicPage

// 返回json格式数据（可以配合ajax请求进行验证）
http://localhost:8080/eonline/retJson



服务已下线（均配置了注解）：
// 注解下线，直接访问，跳转至下线公共页面
http://localhost:8080/aoffline/retStr

// 注解未下线，直接访问，正常返回字符串
http://localhost:8080/aoffline/retStrOnline

// 注解未下线，直接进入对应的页面，但是页面上有两个按钮，分别对应了两个ajax请求。一个已下线，返回下线相关的json数据；一个未下线，正常返回json格式数据。
http://localhost:8080/aoffline/toAjaxPage

// 注解下线，访问动态页面，跳转至下线公共页面
http://localhost:8080/aoffline/retDynamicPage

// 注解下线，直接访问，跳转至下线公共页面。如果是ajax方式发起该请求，则返回下线相关的json数据。
http://localhost:8080/aoffline/retJson
```

#### MyHub


