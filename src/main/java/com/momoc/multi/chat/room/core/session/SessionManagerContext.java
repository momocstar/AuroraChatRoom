package com.momoc.multi.chat.room.core.session;

import com.google.protobuf.MessageLiteOrBuilder;
import com.momoc.multi.chat.room.common.proto.SystemMessage;
import com.momoc.multi.chat.room.common.proto.UserMessage;
import com.momoc.multi.chat.room.common.utils.JsonUtils;
import com.momoc.multi.chat.room.core.event.user.UserLoginEvent;
import com.momoc.multi.chat.room.core.session.vo.UserSessionInfo;
import com.momoc.netty.frame.dispatch.WebsocketRequestContextHolder;
import com.momoc.netty.frame.event.annotation.WebsocketCloseEvent;
import com.momoc.netty.frame.event.annotation.WebsocketConnectEvent;
import com.momoc.netty.frame.event.annotation.WebsocketEvent;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

/**
 * aurora
 * 会话管理器
 * @author momoc
 * @version 1.0
 * @className SessionManagerContext
 * @description
 * @date 2023/7/5 18:17
 */

@Slf4j
@RequiredArgsConstructor
@WebsocketEvent
public class SessionManagerContext {

    /**
     * 所有的会话channel
     *
     */
    static Map<String, UserSessionInfo> sessionInfoMap = new ConcurrentHashMap<>();

    final ChannelManagerContext channelManagerContext;

    @WebsocketConnectEvent
    public void connect(Channel channel){
        ChannelId id = channel.id();
        String token = id.asShortText();
        UserSessionInfo userSessionInfo = new UserSessionInfo();
        userSessionInfo.setChannel(channel);
        sessionInfoMap.put(token, userSessionInfo);
        SystemMessage.sessionBuildResponse_100.Builder builder = SystemMessage.sessionBuildResponse_100.newBuilder();
        builder.setToken(token);
        log.info("链接建立成功，分发token：{}", token);
        channel.writeAndFlush(builder.build());
    }

    public UserSessionInfo getSessionUserInfo(){
        ChannelHandlerContext requestChannel = WebsocketRequestContextHolder.getRequestChannel();
        Channel channel = requestChannel.channel();
        return sessionInfoMap.get(channel.id().asShortText());

    }


    @WebsocketCloseEvent
    public void close(Channel channel){
        log.info("websocket关闭事件2:{}", channel);
        String token = channel.id().asShortText();
        UserSessionInfo userSessionInfo = sessionInfoMap.remove(token);
        channelManagerContext.close(userSessionInfo);
    }

    public static Map<String, UserSessionInfo> getSessionInfoMap() {
        return sessionInfoMap;
    }


    public static UserSessionInfo getSessionInfoMap(String token) {
        return sessionInfoMap.get(token);
    }
    /**
     * 给所有用户发送消息
     * @param messageLiteOrBuilder
     */

    public void sendMsgAllUser(MessageLiteOrBuilder messageLiteOrBuilder){
        for (UserSessionInfo value : sessionInfoMap.values()) {
            Channel channel = value.getChannel();
            channel.writeAndFlush(messageLiteOrBuilder);
        }
    }


    /**
     * 获取会员会话数据
     *
     * @return
     */
    public static UserSessionInfo getUserSessionInfo(){
        ChannelHandlerContext requestChannel = WebsocketRequestContextHolder.getRequestChannel();
        Channel channel = requestChannel.channel();
        return sessionInfoMap.get(channel.id().asShortText());
    }

    /**
     * 登录成功后，在回话session中放入对应的用户信息
     * @param userLoginEvent
     */
    @EventListener(classes = UserLoginEvent.class)
    public void userLoginEventListener(UserLoginEvent userLoginEvent){
        String token = userLoginEvent.getAppUserInfo().getTokenWs();
        UserSessionInfo sessionInfo = SessionManagerContext.getSessionInfoMap(token);
        log.info("加入回话登录中token：{}"  , token);
        sessionInfo.setAppUserInfo(userLoginEvent.getAppUserInfo());
    }





}
