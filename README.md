本项目技术：netty + protobuf、websocket、spring boot 开箱即用。

这个聊天室使用SmartNettyFrame框架。

```
SmartNettyFrame框架为个人开发，封装了netty + protobuf + websocket 提供一些注解来消息分发、用户上下线事件等功能
```
样例地址：https://server.668628.xyz
实现功能：

1、回话管理器：每个weboscket链接都有唯一的id，通过会话id，获取对应的channel ，并发送信息。

子模块：握手的参数

2、心跳检测实现，一定时间内，没有接收到消息端开链接。 已实现。

3、类似过滤器功能，用于一些用户登录认证等 已实现


用户表设计：

目前是想通过一定规则，将用户分散在  user_talbe_X表中。

解决后台管理的疼点：增加一个小标，用来表示用户在哪里表。

需要接入：

微信：微信直接点开链接即可授权到对应用户，直接登录。



>  SmartNettyFrame项目地址

https://github.com/momocstar/SmartNettyFrame



> 前端项目地址

https://gitee.com/hzumomoc/vue-protobuf-websocket-typescript

