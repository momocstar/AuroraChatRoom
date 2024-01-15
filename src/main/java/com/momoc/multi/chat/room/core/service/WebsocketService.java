package com.momoc.multi.chat.room.core.service;

import com.momoc.netty.frame.event.annotation.WebsocketCloseEvent;
import com.momoc.netty.frame.event.annotation.WebsocketConnectEvent;
import com.momoc.netty.frame.event.annotation.WebsocketEvent;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author momoc
 * @version 1.0
 * @className WebsocketService
 * @description
 * @date 2023/7/5 13:54
 */
@Slf4j
@WebsocketEvent
public class WebsocketService {


    @WebsocketCloseEvent
    public void close(Channel channel) {
        log.info("websocket关闭事件:{}", channel);
    }

    @WebsocketConnectEvent
    public void connect(Channel channel) {
        log.info("websocket连接事件:{}", channel);

    }
}
