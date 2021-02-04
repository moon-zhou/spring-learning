### spring IOC

#### 配置方式
1. xml方式，`<bean>`
1. 构造函数：**构造函数方式初始化，可以不写`get/set`方法**
    * xml里未实际配置，直接调用空的构造方法（显示的空的构造方法或者隐式的空的构造方法）
    * `constructor-arg`使用`index`方式初始化，值的位置在代码前后上是可以调整的，但是value类型必须和构造函数的方法一致。
        ```xml
        <constructor-arg index="0" value="1"/>
        <constructor-arg index="1" value="100"/>
        <constructor-arg index="2" value="g"/>
        <constructor-arg index="3" value="xiaomi"/>
      
        <constructor-arg index="0" value="1"/>
        <constructor-arg index="2" value="g"/>
        <constructor-arg index="3" value="xiaomi"/>
        <constructor-arg index="1" value="100"/>
        
        这两种效果都是一样的，但是1对应的数据类型，必须是构造方法里的数据类型，同理其他的也需要一致，否则bean会创建失败。
        org.moonzhou.spring.ioc.bean.Device.Device(java.lang.Integer, java.math.BigDecimal, java.lang.String, java.lang.String)
        ```
    * `constructor-arg`使用`name`方式初始化：如果有多个构造方法，则会根据给出参数个数以及参数类型，自动匹配到对应的构造方法上，进而初始化一个对象。
1. set方法注入:
    * 使用`property & name` + `set`方法，实现实例对象的初始化。
        > 凡是涉及到反射注入值的，属性名统统都不是 Bean 中定义的属性名，而是通过 Java 中的内省机制分析出来的属性名，简单说，就是根据 get/set 方法分析出来的属性名。
    * 使用`p`标签
        > 注意该标签需要加上xml的namespace：xmlns:p="http://www.springframework.org/schema/p"

#### 测试
1. 使用JUnit测试
    > 使用 ClassPathXmlApplicationContext 获取上下文，通过 getBean 获取bean。
1. 使用JUnit+Spring-Test
    > 需要注意的是 spring-test 需要和 Spring 版本保持一致，使用 RELEASE 版本。
1. 示例：`src\test\java\org\moonzhou\spring\ioc\bean`

#### 代码实操顺序（尤其是对于test以及applicationContext.xml）
1. 无参构造函数初始化-(隐式)：`org.moonzhou.spring.ioc.bean.Book`
1. 无参构造函数初始化-(显示)：`org.moonzhou.spring.ioc.bean.User`
1. 有参构造函数初始化-(constructor-arg & index)：`org.moonzhou.spring.ioc.bean.Device`
1. 有参构造函数初始化-(constructor-arg & name)：`org.moonzhou.spring.ioc.bean.Car`
1. set方法初始化：`org.moonzhou.spring.ioc.bean.Blog`

#### 参考
1. [Spring 学习，看这一篇万余字干货就够了（上）](https://zhuanlan.zhihu.com/p/99183015)