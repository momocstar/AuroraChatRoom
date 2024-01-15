package com.momoc.multi.chat.room.core.service.impl;

import com.momoc.multi.chat.room.common.enums.ChannelUserMessageTypeEnum;
import com.momoc.multi.chat.room.common.model.channel.ChannelHistoryMessagePojo;
import com.momoc.multi.chat.room.common.model.channel.ChannelInfoPojo;
import com.momoc.multi.chat.room.common.model.channel.ChannelMemberPojo;
import com.momoc.multi.chat.room.common.proto.ChannelMessage;
import com.momoc.multi.chat.room.common.vo.AppUserInfo;
import com.momoc.multi.chat.room.core.event.user.UserJoinChannelEvent;
import com.momoc.multi.chat.room.core.event.user.UserRegisterEvent;
import com.momoc.multi.chat.room.core.event.user.UserSendMsgEvent;
import com.momoc.multi.chat.room.core.service.IAppUserService;
import com.momoc.multi.chat.room.core.service.IChannelMemberService;
import com.momoc.multi.chat.room.core.service.IChannelService;
import com.momoc.multi.chat.room.core.session.ChannelManagerContext;
import com.momoc.multi.chat.room.core.session.SessionManagerContext;
import com.momoc.multi.chat.room.core.session.vo.UserSessionInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AppUserListenerService {



    final IChannelService channelService;
    final IChannelMemberService channelMemberService;

    final IAppUserService appUserService;

    final ApplicationEventPublisher applicationEventPublisher;
    final ChannelManagerContext channelManagerContext;



    /**
     * 将用户加入到默认频道中，默认频道不运行退出
     * @param userRegisterEvent
     */
    @EventListener(classes = UserRegisterEvent.class)
    public void addToDefaultChannelEventListener(UserRegisterEvent userRegisterEvent){
        List<ChannelInfoPojo> channelInfoPojoList = channelService.getDefaultChannelList();
        Long userid = userRegisterEvent.getUserid();
        ArrayList<ChannelMemberPojo> channelMemberList = new ArrayList<>();
        AppUserInfo appUserInfo = userRegisterEvent.getAppUserInfo();


        //用户加完默认频道后，再发送用户加入频道通知
        for (ChannelInfoPojo channelInfoPojo : channelInfoPojoList) {
            ChannelMemberPojo channelMemberPojo = new ChannelMemberPojo();
            channelMemberPojo.setChannelId(channelInfoPojo.getId());
            channelMemberPojo.setUserid(userid);
            channelMemberList.add(channelMemberPojo);
            /**
             * 用户首次注册兼容，注册时，当前用户在频道不在线，所以加上当前用户在线。
             */
            UserSessionInfo userSessionInfo = SessionManagerContext.getSessionInfoMap(appUserInfo.getTokenWs());
            userSessionInfo.setAppUserInfo(appUserInfo);
            //加入频道在线
            channelManagerContext.onlineMember(channelInfoPojo.getId(), userSessionInfo);
        }
        channelMemberService.saveBatch(channelMemberList);

        for (ChannelInfoPojo channelInfoPojo : channelInfoPojoList) {


            //用户加入频道事件，需要推送
            UserJoinChannelEvent userJoinChannelEvent = new UserJoinChannelEvent(this);
            userJoinChannelEvent.setAppUserInfo(userRegisterEvent.getAppUserInfo());
            userJoinChannelEvent.setChannelId(channelInfoPojo.getId());
            applicationEventPublisher.publishEvent(userJoinChannelEvent);
        }


    }

    @EventListener(classes = UserJoinChannelEvent.class)
    public void userJoinChannelEventListener(UserJoinChannelEvent userJoinChannelEvent){
        AppUserInfo appUserInfo = userJoinChannelEvent.getAppUserInfo();

        ChannelMessage.UserJoinChannelNotice_1006.Builder builder = ChannelMessage.UserJoinChannelNotice_1006.newBuilder();
        builder.setChannelId(userJoinChannelEvent.getChannelId());

        ChannelMessage.UserChannelChatInfoDTO.Builder userChannelInfoBuilder = ChannelMessage.UserChannelChatInfoDTO.newBuilder();
        userChannelInfoBuilder.setHeadPic(appUserInfo.getHeadPic());
        userChannelInfoBuilder.setUserid(appUserInfo.getId());
        userChannelInfoBuilder.setNickname(appUserInfo.getNickname());
        /**
         * @// TODO: 2023/11/3  头衔
         */
        userChannelInfoBuilder.setRankName("test");
        userChannelInfoBuilder.setLevel(0);
        builder.setUserInfo(userChannelInfoBuilder.build());
        channelManagerContext.sendMsgToChannel(userJoinChannelEvent.getChannelId(), builder.build());
    }

    @Async
    @EventListener(classes = UserSendMsgEvent.class)
    public void sendUserMsgEvent(UserSendMsgEvent userSendMsgEvent){
        ChannelMessage.UserReceiveMessage_1005.Builder builder = ChannelMessage.UserReceiveMessage_1005.newBuilder();
        AppUserInfo appUserInfo = userSendMsgEvent.getAppUserInfo();
        ChannelMessage.ChannelMessageDTO.Builder channelMessageDTOBuilder = ChannelMessage.ChannelMessageDTO.newBuilder();

        channelMessageDTOBuilder.setChannelId(userSendMsgEvent.getChannelId());
        channelMessageDTOBuilder.setType(userSendMsgEvent.getType());
        channelMessageDTOBuilder.setUserid(appUserInfo.getId());
        channelMessageDTOBuilder.setContent(userSendMsgEvent.getContent());
        builder.setMessage(channelMessageDTOBuilder.build());
        channelManagerContext.sendMsgToChannel(userSendMsgEvent.getChannelId(), builder.build());

    }

    @Async
    @EventListener(classes = UserSendMsgEvent.class)
    public void savaUserHistoryMessage(UserSendMsgEvent userSendMsgEvent){
        String content = userSendMsgEvent.getContent();
        AppUserInfo appUserInfo = userSendMsgEvent.getAppUserInfo();
        Integer type = userSendMsgEvent.getType();
        Long channelId = userSendMsgEvent.getChannelId();
        //文字类型消息
        if (type == ChannelUserMessageTypeEnum.TEXT_MESSAGE_TYPE.getType()){
            ChannelHistoryMessagePojo channelHistoryMessagePojo = new ChannelHistoryMessagePojo()
                    .setChannelId(channelId)
                    .setUserid(appUserInfo.getId())
                    .setType(type)
                    .setContent(content);
            channelService.saveUserMessage(channelHistoryMessagePojo);
        }
    }

}
