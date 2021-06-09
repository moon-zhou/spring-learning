### spring-boot-profile
springboot里profile的特性使用

#### profile配置文件区分环境
##### 通过不同配置文件
1. 配置文件`application.properties`，配置内容选择需要的配置文件：`spring.profiles.active=dev`。
    * 此配置在`application.yml`里不生效，只能配置在`application.properties`
    * 或者在环境变量里配置对应的值即可
1. 创建对应环境的配置文件：`application-dev.yml`/`application-pre.yml`/`application-prd.yml`
![profile property](./img/profile-property.png)
![profile environment](./img/profile-environment.png)

**以上两种方式，选择一种即可，测试时，适当删除其中一种。**

##### 通过application.yml配置



#### profile区分不同的服务

#### maven打包区分环境