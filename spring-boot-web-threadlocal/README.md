### 关于ThreadLocal在实际操作中使用的示例

#### 背景
> 基于CAS的单点登录系统，在某些平台（微信），会检测url可能存在的安全问题，如果发现，会封禁域名在微信里的访问。
>
> 因为passport是所有系统的门户系统，域名封禁会导致所有系统均不可使用，因此做了备份域名方案，封禁时切换备用域名。
>
> 而CAS的架构是client的jar包 + 服务系统（http+rpc接口），其中client的jar是接入方需要引入的，依据此jar进行302请求验证来进行ticket验证。也是据此来信任的。
> 
> 所以多域名支持时，为了减小影响范围，需要传入HttpServletRequest，通过其获取UA判断是否是微信环境。而很多方法里没有这个参数，需要从filter最上面一层层传递下去。
>
> 此方式不够优雅，后来学习强软弱虚四大引用时，说到了弱引用ThreadLocal，其中之一的使用方式是线程最开始设置值，线程后面使用时直接获取。不需要层层传递，实现优雅，让我们更关注业务逻辑的实现。
>
> 此示例应运而生。

#### 核心
1. client jar 是通过filter来实现的，即本身就是一个filter，接入的系统做好拦截的配置。如果没有登录状态，则拦截到登录页。
1. 拦截到登录的过程，就需要根据HttpServletRequest来判断UA，动态获取跳转登录地址（多域名）。
1. 原始很多获取域名的方法，是没有HttpServletRequest参数的，因此需要一层层从filter里往下传递。
1. 后来看到ThreadLocal可以优雅的实现这个需求，因此有了这个示例。

#### springboot里Filter的实现
1. 手动
    ```
    过滤器实现类：org.moonzhou.filter.auto.AuthFilter
    手动配置类：org/moonzhou/WebThreadLocalApplication.java:15
    ```

2. 自动
    ```
    过滤器实现类：org.moonzhou.filter.manual.LogFilter
    手动配置类：org.moonzhou.config.FilterConfig
    ```

#### 注意点
> 手动和自动配置方式是互斥的，如果同时配置，自动的配置将无法生效，此处注释掉手动配置代码