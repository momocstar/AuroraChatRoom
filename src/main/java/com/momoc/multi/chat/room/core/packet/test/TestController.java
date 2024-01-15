package com.momoc.multi.chat.room.core.packet.test;

import com.momoc.multi.chat.room.common.proto.UserMessage;
import com.momoc.netty.frame.dispatch.annotation.NettyDispatchController;
import com.momoc.netty.frame.dispatch.annotation.NettyDispatchRequest;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author momoc
 * @version 1.0
 * @className TestController
 * @description
 * @date 2023/7/3 17:25
 */
@Slf4j
@NettyDispatchController
public class TestController {

    /**
     * 下面是样例
     * ResponseTest_1000 ，名称上有对应的消息号
     * @param message
     * @return
     */
    @NettyDispatchRequest(openChannelContext = true)
    public UserMessage.testMessageResponse_1003 test(UserMessage.testMessageRequest_1002 message) {
        String test = message.getContent();
//        log.info("接收到客户端的信息:{}", test);
        UserMessage.testMessageResponse_1003.Builder builder = UserMessage.testMessageResponse_1003.newBuilder();
        builder.setContent("hello momoc netty protobuf frame");
        return builder.build();
    }

}
