## Jasypt
### 引入依赖
```
<dependency>
   <groupId>com.github.ulisesbocchio</groupId>
   <artifactId>jasypt-spring-boot-starter</artifactId>
   <version>3.0.4</version>
</dependency>
```
`springboot`可能还需要其他依赖`spring-boot-configuration-processor`等。

### 配置加密秘钥
```
jasypt:
  encryptor:
    password: ${encrypt_key}
```
默认配置为对撑加密秘钥，加解密使用秘钥相同。同时为了安全，**加密密文必须与秘钥进行分离**，否则可以直接解密出密文。一般秘钥配置到环境变量或者是启动命令里。
```
java -jar xxx.jar --jasypt.encryptor.password=encrypt_key
java -Djasypt.encryptor.password=encrypt_key -jar xxx.jar
```

### 测试加解密
- org.moonzhou.jasypt.JasyptTest
- org.moonzhou.jasypt.Jasypt02Test

### 配置密文到yml
格式：ENC(密文)

### 问题及注意点
1. 问题描述：因为应用本身比较复杂，无法本地启动，或者启动较为复杂，单独创建了集成`Jasypt`的`springboot`应用。使用应用进行加解密均没有问题。因此直接将加密内容复制到实际应用中，但是应用在启动过程报错。分析后发现没有示例应用与实际应用关于`Jasypt`的版本不一致，默认加密算法不一致导致。
   ```
   Description:

   Failed to bind properties under 'spring.datasource.password' to java.lang.String:
    
      Reason: Failed to bind properties under 'spring.datasource.password' to java.lang.String
    
   Action:
    
   Update your application's configuration
   ```
   注意点：
   1. 明确加解密时`Jasypt`的版本一致性，使用时尽可能明确加密方式等多维度的参数，尽量不要使用默认参数。
   2. 版本升级时也需要注意原先加密的值，升级后能否正常解密。

### 参考
1. [Spring Boot demo系列（九）：Jasypt](https://zhuanlan.zhihu.com/p/258931087)
2. [jasypt-spring-boot提示Failed to bind properties](https://zhuanlan.zhihu.com/p/144805812)