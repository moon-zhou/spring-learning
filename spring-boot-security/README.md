## springboot security
该模块主要整理用户的认证和鉴权

### OAuth2.0
#### 名词定义
- Third-party application：第三方应用程序，开发者层面来看，又称"客户端"（client）。
- HTTP service：HTTP服务提供商，本文中简称"服务提供商"。
- Resource Owner：资源所有者，本文中又称"用户"（user）。拥有访问资源的权限（账密）。
- User Agent：用户代理，本文中就是指浏览器。
- Authorization server：认证服务器，即服务提供商专门用来处理认证的服务器。
- Resource server：资源服务器，即服务提供商存放用户生成的资源的服务器。它与认证服务器，可以是同一台服务器，也可以是不同的服务器。资源访问时受保护，包含正确的令牌时，方可访问资源。

#### 授权模式
- Authorization Code（授权码模式）
- Implicit（简化模式）
- Resource Owner Password Credentials（密码模式）
- Client Credentials（客户端模式）


### JWT



### 参考
1. [理解OAuth 2.0s](http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html)