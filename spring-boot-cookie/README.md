## Cookie

Cookie除了key和value以外有几个属性：
- httpOnly 是否允许js读取cookie
- secure 是否仅仅在https的链接下，才提交cookie
- domain cookie提交的域
- path cookie提交的path
- maxAge cookie存活时间
- sameSite 同站策略，枚举值：Strict Lax None


### secure
什么的安全的Cookies？安全的cookie是仅可以通过加密的HTTPS连接发送到服务器的cookie。无法通过未加密的HTTP连接将cookie发送到服务器。也就是说，如果设置了setSecure(true)，该Cookie将无法在Http连接中传输，只能是Https连接中传输。

### httpOnly



### 参考
1. [Springboot应用中设置Cookie的SameSite属性](https://juejin.cn/post/6867050293595799560)
2. [如何在Spring Boot中使用Cookies](https://juejin.cn/post/6844903986114330632)