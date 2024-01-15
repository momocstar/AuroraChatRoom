package com.momoc.multi.chat.room.core.filter;

import com.momoc.netty.frame.filter.IWebSocketFilter;
import com.momoc.netty.frame.filter.WebsocketFilter;
import com.momoc.netty.frame.filter.WebsocketFilterChain;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

/**
 * @author momoc
 * @version 1.0
 * @className FilterTest
 * @description
 * @date 2023/7/7 17:37
 */
@Slf4j
//@Order(0)
//@WebsocketFilter
public class FilterTest implements IWebSocketFilter {

    @Override
    public void doFilter(ChannelHandlerContext context, WebsocketFilterChain chain) {
        log.info("过滤器1");
        chain.doNextFilter(context, chain);
    }
}
