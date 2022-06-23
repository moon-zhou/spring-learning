## HTTP client

### 背景
http，



### SpringBoot下的http工具
#### RestTemplate
##### 什么是RestTemplate
`Spring`中封装的通过`Java`代码发送`RestFul`请求的模板类，内置发送`get/post/delete`等请求的方法，在`SpringBoot`中只要导入`spring-boot-starter-web`的依赖可以直接使用。

##### RestTemplate的主要API
| HTTP Method | RestTemplate Methods                          |
| ----------- | --------------------------------------------- |
| Get         | getForObject, getForEntity                    |
| Post        | postForEntity, postForObject, postForLocation |
| PUT         | put                                           |
| any         | exchange, execute                             |
| DELETE      | delete                                        |
| HEAD        | headForHeaders                                |
| OPTIONS     | optionsForAllow                               |


#### OKHttp
TODO

### 参考
1. [SpringBoot图文教程17—上手就会 RestTemplate 使用指南「Get Post」「设置请求头」](https://juejin.cn/post/6844904098219687944)