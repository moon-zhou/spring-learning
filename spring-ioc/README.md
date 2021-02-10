### spring IOC

#### 配置方式
##### xml方式，`<bean>`
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
        org.moonzhou.spring.ioc.xml.bean.Device.Device(java.lang.Integer, java.math.BigDecimal, java.lang.String, java.lang.String)
        ```
    * `constructor-arg`使用`name`方式初始化：如果有多个构造方法，则会根据给出参数个数以及参数类型，自动匹配到对应的构造方法上，进而初始化一个对象。
1. `set`方法注入:
    * 使用`property & name` + `set`方法，实现实例对象的初始化。
        > 凡是涉及到反射注入值的，属性名统统都不是 Bean 中定义的属性名，而是通过 Java 中的内省机制分析出来的属性名，简单说，就是根据 get/set 方法分析出来的属性名。
    * 使用`p`标签
        > 注意该标签需要加上xml的namespace：xmlns:p="http://www.springframework.org/schema/p"
1. 外部`Bean`的注入：自己写的 Bean 一般不会使用这两种方式注入，但是，如果需要引入外部 jar，外部 jar 的类的初始化，有可能需要使用这两种方式。
    * 静态工厂
    * 实例工厂
1. 复杂属性的注入：对象/数组/Map/Properties

##### Java 配置（通过 Java 代码将 Bean 注册到 Spring 容器中）
1. 需要添加`@Configuration`和`@Bean`，Bean 的默认名称是方法名。以上面的案例为例，Bean 的名字是 `sayHello/sayHi/sayHi2`。
1. 配置的加载，是使用`AnnotationConfigApplicationContext`来实现

##### 自动化配置(包扫描，既可以配置xml，也可以通过Java代码进行配置)
1. 注解：`@Configuration` + `@ComponentScan`
    * 根据包位置进行扫描
    * 根据注解类型进行扫描
2. xml
    * 根据包位置进行扫描
    * 根据注解类型进行扫描
    * 注意点：需要添加namespace
        ```xml
        xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="http://www.springframework.org/schema/context
                        http://www.springframework.org/schema/context/spring-context.xsd"
        ```

#### 注入
1. `@Autowired`
1. `@Resources`
1. `@Injected`

##### Inject using Interface type
如果有多个实现，直接按接口类型注入，以上三个注解，都会抛出异常。\[为了使其他示例可以正常运行，Demo001*相关的示例已经将`@Component`注释，避免扫描注入相关依赖时报错\]
```
org.moonzhou.spring.ioc.injection.biz.Demo001AutowiredInterfaceType
org.moonzhou.spring.ioc.injection.biz.Demo001InjectInterfaceType
org.moonzhou.spring.ioc.injection.biz.Demo001ResourceInterfaceType

org.springframework.beans.factory.UnsatisfiedDependencyException: 
Error creating bean with name 'demo001AutowiredInterfaceType': 
Unsatisfied dependency expressed through field 'vehicle'; 
nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException: 
No qualifying bean of type 'org.moonzhou.spring.ioc.injection.service.Vehicle' available: 
expected single matching bean but found 2: fourWheeler,twoWheeler
```

##### Inject using field type as concrete class
按照实现类，具体类名直接注入。
```java
org.moonzhou.spring.ioc.injection.biz.Demo002AutowiredConcreteClass
org.moonzhou.spring.ioc.injection.biz.Demo002InjectConcreteClass
org.moonzhou.spring.ioc.injection.biz.Demo002ResourceConcreteClass

@Resource
TwoWheeler vehicle;
 
@Autowired
TwoWheeler vehicle;
 
@Inject
TwoWheeler vehicle;
```

##### injecting using field name
根据注入的属性名为bean的name来进行注入。
```java
org.moonzhou.spring.ioc.injection.biz.Demo003AutowiredFiledName
org.moonzhou.spring.ioc.injection.biz.Demo003InjectFiledName
org.moonzhou.spring.ioc.injection.biz.Demo003ResourceFiledName

@Resource
Vehicle twoWheeler;
 
@Autowired
Vehicle twoWheeler;
 
@Inject
Vehicle twoWheeler;
```

##### Qualifier with default bean name
注入时仍使用接口类名，属性名不使用bean name（第一种注入方式，报错的方式）。但是需要配合`@Qualifier`显示表名注入的bean name。
```java
org.moonzhou.spring.ioc.injection.biz.Demo004AutowiredQualifierBeanName
org.moonzhou.spring.ioc.injection.biz.Demo004InjectQualifierBeanName
org.moonzhou.spring.ioc.injection.biz.Demo004ResourceQualifierBeanName

@Resource
@Qualifier("fourWheeler")
Vehicle vehicle;

@Autowired
@Qualifier("twoWheeler")
private Vehicle vehicle;

@Inject
@Qualifier("threeWheelerVehcile")
Vehicle vehicle;
```

#### 测试
1. 使用JUnit测试
    > 使用 ClassPathXmlApplicationContext 获取上下文，通过 getBean 获取bean。
1. 使用JUnit+Spring-Test
    > 需要注意的是 spring-test 需要和 Spring 版本保持一致，使用 RELEASE 版本。
1. 示例：`src\test\java\org\moonzhou\spring\ioc\xml\bean`

#### 代码实操顺序（尤其是对于test以及applicationContext.xml）
1. 无参构造函数初始化-(隐式)：`org.moonzhou.spring.ioc.xml.bean.Book`
1. 无参构造函数初始化-(显示)：`org.moonzhou.spring.ioc.xml.bean.User`
1. 有参构造函数初始化-(constructor-arg & index)：`org.moonzhou.spring.ioc.xml.bean.Device`
1. 有参构造函数初始化-(constructor-arg & name)：`org.moonzhou.spring.ioc.xml.bean.Car`
1. set方法初始化：`org.moonzhou.spring.ioc.xml.bean.Blog`
1. 外部`Bean`的注入
    * `org.moonzhou.spring.ioc.xml.util.OkHttpUtils`
    * `org.moonzhou.spring.ioc.xml.util.OkHttpUtils2`
1. 复杂属性的注入
    * `org.moonzhou.spring.ioc.xml.bean.UserComplex`
    * `org.moonzhou.spring.ioc.xml.bean.UserComplex2`
    * `org.moonzhou.spring.ioc.xml.bean.UserComplex3`
    * `org.moonzhou.spring.ioc.xml.bean.UserComplex4`
    * `org.moonzhou.spring.ioc.xml.bean.UserComplex5`
1. java配置方式：`org.moonzhou.spring.ioc.configuration`
1. 包扫描配置：`org.moonzhou.spring.ioc.scan`

#### 参考
1. [Spring 学习，看这一篇万余字干货就够了（上）](https://zhuanlan.zhihu.com/p/99183015)
1. [@Autowired, @Resource and @Inject](http://javainsimpleway.com/autowired-resource-and-inject-2/)