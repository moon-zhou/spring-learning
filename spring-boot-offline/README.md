### spring boot offline
下线web应用下http接口的组件模块，使用springboot应用作为演示实例。

#### 核心设计思想
* aop切面，判断出当前请求已下线直接处理
* 下线请求可以动态配置，但是需要结合分布式配置中心（此示例不实现该特性，且该特性只针对execution配置）
* 表单请求：统一页面展示
* ajax请求：json格式的下线提示


#### 实现方式
1. annotation注解
    * 自定义注解，定义下线的字段及可配置的值
    * 对需要下线的接口添加注解和对应的值
    * aop实现注解的拦截：拦截到自定义注解同时下线的值配置正确；获取到当前`HttpServletRequest`，判断出是ajax还是普通form请求，进行不同的运行时异常抛送
    * 针对不同的运行时异常，进行不同的处理
2. execution表达式
    ```
    execution(* org.moonzhou..service.*.*(..))
    ```
    * execution 表达式的主体
    * 第一个* 代表任意的返回值
    * `org.moonzhou` aop所横切的包名
    * 包后面.. 表示当前包及其子包
    * 第二个* 表示类名，代表所有类
    * .*(..) 表示任何方法,括号代表参数 .. 表示任意参数

    ```
    execution(* org.moonzhou.offline.web.execution.EOfflineController.*Off(..))
    ```
    * EOfflineController类下以Off为结尾的方法，任意入参，任意返回
#### 测试
1. 注解方式下线测试案例
```
服务未下线(均未配置注解)：
// 直接访问，返回字符串
http://localhost:8080/eonline/retStr

// 访问页面，动态参数回显
http://localhost:8080/eonline/retDynamicPage

// 返回json格式数据（配合ajax请求进行验证，即后面四个按钮的页面上发起）
http://localhost:8080/eonline/retJson



服务已下线（均配置了注解）：
// 注解下线，直接访问，跳转至下线公共页面
http://localhost:8080/aoffline/retStr

// 注解未下线，直接访问，正常返回字符串
http://localhost:8080/aoffline/retStrOnline

// 注解未下线，直接进入对应的页面，但是页面上有四个按钮，其中两个对应annotation方式下线测试，分别对应了两个ajax请求。一个已下线，返回下线相关的json数据；一个未下线，正常返回json格式数据。
http://localhost:8080/aoffline/toAjaxPage

// 注解下线，访问动态页面，跳转至下线公共页面
http://localhost:8080/aoffline/retDynamicPage

// 注解下线，直接访问，跳转至下线公共页面。如果是ajax方式发起该请求，则返回下线相关的json数据。
http://localhost:8080/aoffline/retJson
```

1. execution方式下线测试案例
```
服务未下线（execution不配置目录下需要提供服务的类）：
// 直接访问，返回字符串
http://localhost:8080/eonline/retStr

// 访问页面，动态参数回显
http://localhost:8080/eonline/retDynamicPage

// 返回json格式数据（配合ajax请求进行验证，即四个按钮的页面上发起）
http://localhost:8080/eonline/retJson

服务已下线：
// 直接访问，返回字符串
http://localhost:8080/eoffline/indexOff

// 访问页面，动态参数回显
http://localhost:8080/eoffline/retDynamicPageOff

// 返回json格式数据（配合ajax请求进行验证，即四个按钮的页面上发起）
http://localhost:8080/eoffline/retJsonOff

execution配置方法级下线，方法以Off结尾的下线，其中一个方法没有以Off结尾，所以服务仍然可以访问：
http://localhost:8080/eoffline/index
```
#### MyHub
[本示例](https://github.com/moon-zhou/spring-learning/tree/master/spring-boot-offline)

