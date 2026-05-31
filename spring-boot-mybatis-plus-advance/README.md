# MyBatis-Plus

## 多数据源
### 使用方法
1. `dynamic-datasource-spring-boot-starter`
   ```
   spring-boot 1.5.x 2.x.x
   <dependency>
       <groupId>com.baomidou</groupId>
       <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
       <version>${version}</version>
   </dependency>
   
   spring-boot3
   <dependency>
       <groupId>com.baomidou</groupId>
       <artifactId>dynamic-datasource-spring-boot3-starter</artifactId>
       <version>${version}</version>
   </dependency>
   ```
2. 配置数据源
   ```
   spring:
     datasource:
       dynamic:
         primary: master #设置默认的数据源或者数据源组,默认值即为master
         strict: false #严格匹配数据源,默认false. true未匹配到指定数据源时抛异常,false使用默认数据源
         datasource:
           master:
             url: jdbc:mysql://xx.xx.xx.xx:3306/dynamic
             username: root
             password: 123456
             driver-class-name: com.mysql.jdbc.Driver # 3.2.0开始支持SPI可省略此配置
           slave_1:
             url: jdbc:mysql://xx.xx.xx.xx:3307/dynamic
             username: root
             password: 123456
             driver-class-name: com.mysql.jdbc.Driver
           slave_2:
             url: ENC(xxxxx) # 内置加密,使用请查看详细文档
             username: ENC(xxxxx)
             password: ENC(xxxxx)
             driver-class-name: com.mysql.jdbc.Driver
          #......省略
          #以上会配置一个默认库master，一个组slave下有两个子库slave_1,slave_2
   ```

   ```
   # 多主多从                      纯粹多库（记得设置primary）                   混合配置
   spring:                               spring:                               spring:
     datasource:                           datasource:                           datasource:
       dynamic:                              dynamic:                              dynamic:
         datasource:                           datasource:                           datasource:
           master_1:                             mysql:                                master:
           master_2:                             oracle:                               slave_1:
           slave_1:                              sqlserver:                            slave_2:
           slave_2:                              postgresql:                           oracle_1:
           slave_3:                              h2:                                   oracle_2:
   ```



## 动态表名



## 数据安全保护




## 参考
1. [多数据源](https://baomidou.com/pages/a61e1b/#dynamic-datasource)