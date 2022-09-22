## springboot 自定义校验

### 背景
对于http接口，java在做服务端开发的时候，往往是需要进行入参合法性校验的，如果仅仅依赖前端校验，是不合理的。如果安全性较高，必要时需要将入参加密传输，同时进行签名。

本次针对后端服务参数的校验，可能不同的接口，都有相同的字段，而且校验逻辑相同，为了避免代码的重复，同时使得代码更优雅简洁，使用自定义注解校验的方式，是一种比较好的选择。

而此标准来自于`JSR 303 - Bean Validation`。

### 使用方式
#### 引入依赖
```
<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
    <version>2.0.1.Final</version>
</dependency>
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>6.2.5.Final</version>
</dependency>
```
或者直接引入
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
如果是分别引入，注意需要填写版本号。

#### JSR303定义的校验类型
```
空检查
@Null			验证对象是否为null
@NotNull		验证对象是否不为null, 无法查检长度为0的字符串
@NotBlank		检查约束字符串是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
@NotEmpty		检查约束元素是否为NULL或者是EMPTY. 

Booelan检查
@AssertTrue		验证 Boolean 对象是否为 true  
@AssertFalse	验证 Boolean 对象是否为 false  

长度检查
@Size(min=, max=)		验证对象（Array,Collection,Map,String）长度是否在给定的范围之内  
@Length(min=, max=)		验证注解的元素值长度在min和max区间内

日期检查
@Past		验证 Date 和 Calendar 对象是否在当前时间之前  
@Future		验证 Date 和 Calendar 对象是否在当前时间之后  
@Pattern	验证 String 对象是否符合正则表达式的规则

数值检查，建议使用在Stirng,Integer类型，不建议使用在int类型上，因为表单值为“”时无法转换为int，但可以转换为Stirng为"",Integer为null
@Min			验证 Number 和 String 对象是否大等于指定的值  
@Max			验证 Number 和 String 对象是否小等于指定的值  
@DecimalMax		被标注的值必须不大于约束中指定的最大值. 这个约束的参数是一个通过BigDecimal定义的最大值的字符串表示.小数存在精度
@DecimalMin		被标注的值必须不小于约束中指定的最小值. 这个约束的参数是一个通过BigDecimal定义的最小值的字符串表示.小数存在精度
@Digits			验证 Number 和 String 的构成是否合法  
@Digits(integer=,fraction=)		验证字符串是否是符合指定格式的数字，interger指定整数精度，fraction指定小数精度。

@Range(min=, max=)	验证注解的元素值在最小值和最大值之间
@Range(min=10000,max=50000,message="range.bean.wage")
private BigDecimal wage;

@Valid 递归的对关联对象进行校验, 如果关联对象是个集合或者数组,那么对其中的元素进行递归校验,如果是一个map,则对其中的值部分进行校验.(是否进行递归验证)
@CreditCardNumber信用卡验证
@Email  验证是否是邮件地址，如果为null,不进行验证，算通过验证。
@ScriptAssert(lang= ,script=, alias=)
@URL(protocol=,host=, port=,regexp=, flags=)
```

#### Hibernate Validator 附加的 constraint
```
@Email	被注释的元素必须是电子邮箱地址
@Length	被注释的字符串的大小必须在指定的范围内
@NotEmpty	被注释的字符串的必须非空
@Range	被注释的元素必须在合适的范围内
```

#### 自定义注解
1. 自定义对应校验的注解：`org.moonzhou.validation.annotation.IdNum`和`org.moonzhou.validation.annotation.MobilePhoneNumber`
2. 如果逻辑不复杂，自定义注解里面，`@Constraint`无需配置，只需要添加正则`@Pattern`配置
3. 如果逻辑复杂，`@Constraint`配置具体的验证器，比如`org.moonzhou.validation.validator.IdNumValidator`
4. 实现具体的验证器逻辑：实现`ConstraintValidator`接口，实现其`isValid`的方法，返回`true`表示验证通过。

#### `@Valid` 和 `@Validated`
相同点:`@Valid`注解和`@Validated`注解都是开启校验功能的注解

不同点:
   - `@Validated`注解是Spring基于 `@Valid` 注解的进一步封装,并提供比如分组,分组顺序的高级功能
   - 使用位置不同:
      - `@Valid`注解 : 可以使用在方法,构造函数,方法参数和成员属性上(用在成员属性上可以进行**级联校验**)
      - `@Validated`注解 : 可以用在类上,方法和方法参数上. 但是不能用在成员属性上

#### 分组(代码库暂未实现)
先定义分组接口（接口什么都不需要，空的就可以）：
```
public interface Insert {
}
public interface Update {
}
```
在需要校验的bean上加上分组注解：
```
@NotBlank(groups = {Update.class}, message = "ID不能为空")
private String id;

@NotBlank(groups = {Insert.class, Update.class}, message = "名称不能为空")
@Size(groups = {Insert.class, Update.class}, max = 32, message = "名称最大长度为32")
private String name;
```
根据需要，在Controller处理请求中加入 @Validated 并引入需要校验的分组（未引入分组则都校验）
```
@PostMapping("/insert")
public int insert(@RequestBody @Validated({Insert.class}) HospitalRequest request) {
    return hospitalService.insert(request);
}

@PostMapping("/update")
public int update(@RequestBody @Validated({Update.class}) HospitalRequest request) {
    return hospitalService.update(request);
}
```

#### 嵌套验证
controller方法入口使用`@Validated`，添加到父类参数前表示父类参数需要校验。
```
@PostMapping("/add")
public void add(@RequestBody @Validated HospitalRequest request) {
    add();
}
```
然后把`@Valid`放到需要验证的集合/嵌套对象上就可以了:
```
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HospitalRequest {
    /**
     * ID
     */
    @NotBlank(groups = {Update.class}, message = "ID不能为空")
    private String id;
    /**
     * 名称
     */
    @NotBlank(groups = {Insert.class, Update.class}, message = "名称不能为空")
    @Size(groups = {Insert.class, Update.class}, max = 32, message = "名称最大长度为32")
    private String name;
    /**
     * 科室
     */
    @Valid //嵌套验证必须用@Valid，List或者单独的对象均可
    @NotBlank(groups = {Insert.class, Update.class}, message = "departmentList不能为空")
    @Size(groups = {Insert.class, Update.class}, min = 1, message = "至少要有一个属性")
    private List<Department> departmentList;
}
```

#### 使用BindingResult接收校验结果信息
使用注解进行校验的时候，我们可以通过`BindingResult`来收集校验结果信息，具体操作如下：

Controller中，在`@Valid`或`@Validated`修饰的参数后跟上`BindingResult`参数（`@Valid`或`@Validated` 和 `BindingResult` 是一 一对应的，如果有多个`@Valid`或`@Validated`，那么每个`@Valid`或`@Validated`后面都需要添加`BindingResult`用于接收bean中的校验信息）
```
@PostMapping("/insert")
public int insert(@RequestBody @Validated HospitalRequest request, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        List<String> collect = bindingResult.getFieldErrors().stream().map(
                DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        StringBuilder errorMsg = new StringBuilder();
        for (String s : collect) {
            errorMsg.append(s);
            errorMsg.append(",");
        }
        errorMsg = new StringBuilder(errorMsg.substring(0, errorMsg.length() - 1));
        log.error("校验未通过：{}", errorMsg.toString());
        Assert.state(Boolean.FALSE, errorMsg.toString());
    }
    return hospitalService.insert(request);
}
```

#### 统一异常处理
在日常开发中，我们可能需要让校验返回指定的信息或对象，这时我们就可以进行统一异常处理：
```
package com.app.config;

import com.framework.common.domain.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 参数校验异常处理
 */
@Slf4j
@RestControllerAdvice
public class BadRequestExceptionHandler {

    /**
     * 校验错误拦截处理
     *
     * @param exception 错误信息集合
     * @return ErrorResponse 错误响应，当HTTP响应状态码不为200时，使用该响应返回
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ErrorResponse validateRequestException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        StringBuilder errorMsg = new StringBuilder();
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError objectError : errors) {
                FieldError fieldError = (FieldError) objectError;
                if (log.isDebugEnabled()) {
                    log.error("Data check failure : object: {},field: {},errorMessage: {}",
                            fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
                }
                errorMsg.append(objectError.getDefaultMessage());
                errorMsg.append(",");
            }
            errorMsg = new StringBuilder(errorMsg.substring(0, errorMsg.length() - 1));
        }
        return new ErrorResponse("ILLEGAL_ARGUMENT_ERROR", errorMsg.toString());
    }
}
```

返回的自定义响应体如下：
```
package com.framework.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 错误响应，当HTTP响应状态码不为200时，使用该响应返回
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {

	/**
	 * 错误码
	 */
	private String code;

	/**
	 * 错误信息
	 */
	private String message;

}
```


### 其他
1. 注解的`@Target`值：
   ```java
     @Target:注解的作用目标
     @Target(ElementType.TYPE)   //接口、类、枚举
     @Target(ElementType.FIELD) //字段、枚举的常量
     @Target(ElementType.METHOD) //方法
     @Target(ElementType.PARAMETER) //方法参数
     @Target(ElementType.CONSTRUCTOR)  //构造函数
     @Target(ElementType.LOCAL_VARIABLE)//局部变量
     @Target(ElementType.ANNOTATION_TYPE)//注解
     @Target(ElementType.PACKAGE) ///包
   ```
2. service层使用方式
   1. 类上添加`@Validated`
   2. 方法入参添加`@Valid`

### 参考
1. [【每日鲜蘑】Spring Boot 自定义注解进行参数校验](https://juejin.cn/post/6844903904216350727)
2. [Java中的三种校验注解的使用说明！分析@Valid和@Validated以及@PathVariable的的具体使用](https://juejin.cn/post/7090327624970895397)
3. [@validate或@valid注解进行数据校验的解决方案](https://www.cnblogs.com/curtinliu/p/14098692.html)