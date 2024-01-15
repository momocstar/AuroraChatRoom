package com.momoc.multi.chat.room;

import com.momoc.netty.frame.config.EnableMomocNettyFrame;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMomocNettyFrame(protoClassScanPath = {"com.momoc.multi.chat.room.common.proto"})
@MapperScan("com.momoc.multi.chat.room.common")
@SpringBootApplication
public class AppRun {

    /**
     * 待解决问题：
     * 1、回顾以前学习netty知识点，搞清楚netty 的运行机制原理
     * 2、写一个基本的mvc转发功能，通过注解执行对应的业务。
     * 简单梳理下实现逻辑：
     * （1）扫码代码包下所有的类，拿到有NettyDispatchController注解的类
     * （2）解析类下带 NettyDispatchRequest 注解的方法，把url映射到map中
     * （3）在编解码器，解析对应的数据，并获取对应方法的实体参数，注入进去。
     * (4) 对应的方法执行业务，返回内容
     * 3、引入protobuf ，并使用protobuf，
     * 4、Vue项目中，使用protobuf，并进行websocket连接，接收消息
     * 5、暂时想到这么多
     * 心跳检测解决微博页：
     * https://blog.csdn.net/weixin_42089175/article/details/99466397
     * <p>
     * websocket 协议解释：https://zhuanlan.zhihu.com/p/581980655
     * 问题 netty 是怎么处理，websocket 协议的？
     * <p>
     * 发送消息内容0： 0801120130  ->
     * 1000 0000 0 0010 00100100000000100110000
     * FIN, 长度为 1 比特
     * <p>
     * //另外一种类型实现protobuf vue
     * http://vuepress.wmm66.com/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91/vue/vue%E9%A1%B9%E7%9B%AE%E4%B8%AD%E4%BD%BF%E7%94%A8protobuf.html#ajax%E5%B0%81%E8%A3%85
     * <p>
     * <p>
     * npx pbjs -t json-module -w commonjs -o src/proto/proto.js src/proto/*.proto
     * // 在request下添加一個方法，方便用於處理請求參數
     * request.create = function (protoName, obj) {
     * const pbConstruct = protoRoot.lookup(protoName)
     * return pbConstruct.encode(obj).finish()
     * }
     */

    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
    }

}
