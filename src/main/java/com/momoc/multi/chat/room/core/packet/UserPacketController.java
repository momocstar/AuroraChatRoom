package com.momoc.multi.chat.room.core.packet;

import com.momoc.multi.chat.room.common.enums.NettyPacketConstants;
import com.momoc.multi.chat.room.common.exception.BaseException;
import com.momoc.multi.chat.room.common.proto.ChannelMessage;
import com.momoc.multi.chat.room.common.proto.UserMessage;
import com.momoc.multi.chat.room.common.vo.AppUserInfo;
import com.momoc.multi.chat.room.core.event.user.UserSendMsgEvent;
import com.momoc.multi.chat.room.core.session.SessionManagerContext;
import com.momoc.multi.chat.room.core.session.vo.UserSessionInfo;
import com.momoc.netty.frame.dispatch.annotation.NettyDispatchController;
import com.momoc.netty.frame.dispatch.annotation.NettyDispatchRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author momoc
 * @version 1.0
 * @className UserPacketController
 * @description
 * @date 2023/7/5 18:06
 */
@Slf4j
@NettyDispatchController
public class UserPacketController extends BasePacketController{

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;


//    @NettyDispatchRequest(packetReqId = NettyPacketConstants.MESSAGE_USER_LOGIN)
//    public UserMessage.sessionBuildResponse_100 Login(UserMessage.userLoginRequest userLoginRequest) {
//        UserMessage.sessionBuildResponse_100.Builder builder = UserMessage.sessionBuildResponse_100.newBuilder();
//        return builder.build();
//    }


    @NettyDispatchRequest
    public void UserSendMessage(ChannelMessage.UserSendMessage_1004 message) throws BaseException, Exception {
        UserSessionInfo userInfo = getUserInfo();
        AppUserInfo appUserInfo = userInfo.getAppUserInfo();
        if (appUserInfo == null){
            return;
        }
        ChannelMessage.ChannelMessageDTO channelMessageDTO = message.getMessage();


        UserSendMsgEvent userSendMsgEvent = new UserSendMsgEvent(this);
        userSendMsgEvent.setAppUserInfo(appUserInfo);
        log.info("接收到的消息:{}", channelMessageDTO.getContent());
        userSendMsgEvent.setContent(channelMessageDTO.getContent());
        userSendMsgEvent.setType(channelMessageDTO.getType());
        userSendMsgEvent.setChannelId(message.getMessage().getChannelId());
        applicationEventPublisher.publishEvent(userSendMsgEvent);
    }
}
