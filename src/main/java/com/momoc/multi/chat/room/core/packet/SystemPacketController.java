package com.momoc.multi.chat.room.core.packet;

import com.momoc.multi.chat.room.common.proto.SystemMessage;
import com.momoc.netty.frame.dispatch.annotation.NettyDispatchController;
import com.momoc.netty.frame.dispatch.annotation.NettyDispatchRequest;

/**
 * @author momoc
 * @version 1.0
 * @className SystemPacketControoler
 * @description
 * @date 2023/7/12 17:55
 */
@NettyDispatchController
public class SystemPacketController {

    //仅接收不进行处理
    @NettyDispatchRequest
    public void systemHeartPop(SystemMessage.heartPopMessage_200 heartPop){


    }
}
