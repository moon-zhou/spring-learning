### 测试请求参数映射
1. `@PathVariable`方式
1. `@RequestParam`方式
1. `@RequestBody`方式
1. 无注解方式
1. `request.getParameter`方式，纯手工

#### @PathVariable
1. 参数必须是restful格式，作为请求的链接一部分
1. 不显示映射名称时，直接使用注解修饰的形参进行映射
1. 不显示设置修饰的字段的必要性时，默认必传
1. Data类型需要DateTimeFormat
1. 正常映射boolean

#### @RequestParam
1. 既可以使用链接传参，亦可以使用body（form-data）进行传参
1. 不显示映射名称时，直接使用注解修饰的形参进行映射
1. 不显示设置修饰的字段的必要性时，默认必传
1. Data类型需要DateTimeFormat
1. 正常映射boolean

#### @RequestBody
1. 发起请求需要添加header，<code>Content-Type=application/json</code>
1. 如果时使用postman发起请求，那么Body里的参数必须选择raw方式，否则报错，这也就限制了该方式的参数大对象只有一个
    ```
    org.springframework.web.HttpMediaTypeNotSupportedException: Content type 'multipart/form-data;...not supported
    ```
1. 就提的bean方式接收，或者map方式均可，bean方式时需要注意，date类型不适用`@DateTimeFormat`也是可以成功注入的

#### 无注解方式
1. 日期类型字段需要添加<code>@DateTimeFormat(pattern = "yyyy-MM-dd")</code>
1. get请求，参数直接通过连接传递可以映射成功
1. 通过form-data也可以映射成功
1. body里raw方式时不能映射进来的

#### request.getParameter

