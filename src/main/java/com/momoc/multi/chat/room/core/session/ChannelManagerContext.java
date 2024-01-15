package com.momoc.multi.chat.room.core.session;

import com.google.protobuf.MessageLiteOrBuilder;
import com.momoc.multi.chat.room.common.model.channel.ChannelMemberPojo;
import com.momoc.multi.chat.room.common.proto.ChannelMessage;
import com.momoc.multi.chat.room.common.vo.AppUserInfo;
import com.momoc.multi.chat.room.core.event.user.UserLoginEvent;
import com.momoc.multi.chat.room.core.service.IChannelService;
import com.momoc.multi.chat.room.core.session.vo.UserSessionInfo;
import com.momoc.netty.frame.event.annotation.WebsocketEvent;
import io.netty.channel.Channel;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author momoc
 * @version 1.0
 * @className ChannelManagerContext
 * @description
 * @date 2023/9/1 16:28
 */
@RequiredArgsConstructor
@WebsocketEvent
public class ChannelManagerContext {


    //频道在线成员
    public static Map<Long, Map<String, UserSessionInfo>> channelOnLineMap = new ConcurrentHashMap<>();


    final IChannelService channelService;

    /**
     *
     * @param userLoginEvent
     */
    @EventListener(classes = UserLoginEvent.class)
    public void userLoginEventListener(UserLoginEvent userLoginEvent){
        AppUserInfo appUserInfo = userLoginEvent.getAppUserInfo();

        Long userid = appUserInfo.getId();
        String token = appUserInfo.getTokenWs();
        //查询用户所在频道
        List<ChannelMemberPojo> channelMemberPojoList = channelService.findUserChannel(userid);
        for (ChannelMemberPojo channelMemberPojo : channelMemberPojoList) {
            Long channelId = channelMemberPojo.getChannelId();
            Map<String, UserSessionInfo> userSessionInfos = channelOnLineMap.computeIfAbsent(channelId, k -> new ConcurrentHashMap<>());
            UserSessionInfo userSessionInfo = SessionManagerContext.getSessionInfoMap(token);
            userSessionInfos.put(token, userSessionInfo);

            //发送上线通知
            for (UserSessionInfo value : userSessionInfos.values()) {
                Channel channel = value.getChannel();
                ChannelMessage.UserOnlineStatusNotice_1007.Builder builder = ChannelMessage.UserOnlineStatusNotice_1007.newBuilder();
                builder.setChannelId(channelId)
                        .setUserid(userid)
                        .setOnline(true);
                channel.writeAndFlush(builder.build());
            }
        }
    }

    /**
     * 移除在线人员
     * @param userSessionInfo
     */
    public  void close(UserSessionInfo userSessionInfo){
        String token = userSessionInfo.getChannel().id().asShortText();
        AppUserInfo appUserInfo = userSessionInfo.getAppUserInfo();
        Long userid = appUserInfo.getId();
        List<ChannelMemberPojo> channelMemberPojoList = channelService.findUserChannel(appUserInfo.getId());
        for (ChannelMemberPojo channelMemberPojo : channelMemberPojoList) {
            Long channelId = channelMemberPojo.getChannelId();
            Map<String, UserSessionInfo> userSessionInfoMap =  channelOnLineMap.computeIfAbsent(channelId,  k-> new ConcurrentHashMap<>());
            userSessionInfoMap.remove(token);
            //发送下线通知
            for (UserSessionInfo value : userSessionInfoMap.values()) {
                Channel channel = value.getChannel();
                ChannelMessage.UserOnlineStatusNotice_1007.Builder builder = ChannelMessage.UserOnlineStatusNotice_1007.newBuilder();
                builder.setChannelId(channelId)
                        .setUserid(userid)
                        .setOnline(false);
                channel.writeAndFlush(builder.build());
            }
        }
    }


    /**
     * 给频道的人发送消息
     * @param channelId
     * @param message
     */
    public void sendMsgToChannel(Long channelId, MessageLiteOrBuilder message){
        Map<String, UserSessionInfo> userSessionInfoMap =  channelOnLineMap.computeIfAbsent(channelId,  k-> new ConcurrentHashMap<>());;
        //频道没有在线人员
        for (UserSessionInfo value : userSessionInfoMap.values()) {
            value.getChannel().writeAndFlush(message);
        }
    }



    public static Collection<UserSessionInfo> getChannelOnlineMember(Long channelId){
        Map<String, UserSessionInfo> orDefault = channelOnLineMap.computeIfAbsent(channelId,  k-> new ConcurrentHashMap<>());
        return orDefault.values();
    }
    public  void onlineMember(Long channelId, UserSessionInfo userSessionInfo){
        Map<String, UserSessionInfo> orDefault = channelOnLineMap.computeIfAbsent(channelId, k->new ConcurrentHashMap<>());
        orDefault.put(userSessionInfo.getAppUserInfo().getTokenWs(), userSessionInfo);
    }
}
