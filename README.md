本项目技术：netty + protobuf、websocket、spring boot 开箱即用。

这个聊天室使用SmartNettyFrame框架。

```
SmartNettyFrame框架为个人开发，封装了netty + protobuf + websocket 提供一些注解来消息分发、用户上下线事件等功能
```



注：实现每个功能需要画对应的流程图。（对自己说的）

实现功能：

1、回话管理器：每个weboscket链接都有唯一的id，通过会话id，获取对应的channel ，并发送信息。

发送消息最好写成一个工具类。 工具类没有写

子模块：握手的参数

2、心跳检测实现，一定时间内，没有接收到消息端开链接。 已实现。

3、类似过滤器功能，用于一些用户登录认证等 已实现

4、接入spring security  oauth2 单点登录，并完善用户信息表。 优先级。
https://blog.51cto.com/u_15281317/2942331

用户表设计：

目前是想通过一定规则，将用户分散在  user_talbe_X表中。

解决后台管理的疼点：增加一个小标，用来表示用户在哪里表。

需要接入：

微信：微信直接点开链接即可授权到对应用户，直接登录。



5、学习一些前端移动框架、看看有没有一些脚手架进行快速开发。

6、UI大致跟qq频道差不多。





前端H5模板项目：https://sunniejs.github.io/vue-h5-template/guide/vue3/start.html

https://ftf.jd.com/wot-design/#/components/introduction
