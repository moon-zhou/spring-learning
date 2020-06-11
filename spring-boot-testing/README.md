### 专用于测试的模块
1. 测试日志打印：问题
    1. 调整日志打印级别为debug之后，会报如下错误
        ```
        org.apache.tomcat.jni.LibraryNotFoundError: Can't load library: ...\tcnative-1.dll
        ```
    1. 原因为：因为Tomcat中的connector为了提高性能，采用了加载与操作系统绑定（非跨平台）的本地库的方式，比如Windows系统中就是.dll动态链接库。上述异常中找不到的两个.dll库文件，默认会去Tomcat的bin目录下去找，但是由于SpringBoot的Tomcat是嵌入式的，没有这两个.dll，所以只好自己去官网下载，到如下官方地址下载，并放到C:\Windows\System32\目录下：
       ```
           http://archive.apache.org/dist/tomcat/tomcat-connectors/native/1.2.14/binaries/
       ```
1. 单纯输出日志时，占位符方式，比append之后直接输出耗时高一丢丢