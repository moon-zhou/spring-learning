#### 问题记录
1. 国际化时，中文乱码，原因为文件编码格式，按如下方式修改IDEA配置文件即可
    ```
   1.首先我们的IDEA文件编码一般都修改为utf-8(setting-->file encodings--->Global Encoding 和 Project Encoding 都设置为UTF-8)
       
   2.对于 Properties 文件，重要属性 Transparent native-to-ascii conversion 主要用于转换 ascii，一般都要勾选，不然 Properties 文件中的注释显示的都不会是中文。
    ```
1. 全局异常处理的三种方式
    1. 使用@ExceptionHandler注解配合 @ControllerAdvice注解使用实现异常处理
    2. 实现HandlerExceptionResolver接口来管理异常
    3. 使用@Around注解抓取JoinPoint（切面）的proceed()方法来环绕管理方法抛出的异常
    
    如果既配置了1，也配置2，则只会执行第二种异常处理的方式。具体测试的时候，根据实际情况，注释掉其中一种。