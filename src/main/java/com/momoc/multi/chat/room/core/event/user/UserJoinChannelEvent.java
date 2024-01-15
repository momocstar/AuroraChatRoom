package com.momoc.multi.chat.room.core.event.user;

import com.momoc.multi.chat.room.common.vo.AppUserInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author momoc
 * @version 1.0
 * @className UserLoginEvent
 * @description
 * @date 2023/9/1 16:04
 */

@Setter
@Getter
public class UserJoinChannelEvent extends ApplicationEvent {
    public UserJoinChannelEvent(Object source) {
        super(source);
    }

    AppUserInfo appUserInfo;

    //加入的频道
    Long channelId;


}
