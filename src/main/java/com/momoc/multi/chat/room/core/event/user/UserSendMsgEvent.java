package com.momoc.multi.chat.room.core.event.user;

import com.momoc.multi.chat.room.common.vo.AppUserInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author momoc
 * @version 1.0
 * @className UserSendMsgEvent
 * @description
 * @date 2023/7/12 17:42
 */
@Setter
@Getter
public class UserSendMsgEvent extends ApplicationEvent {

    //发送着
    AppUserInfo appUserInfo;
    /**
     * 发送类型
     */

    Integer type;
    String content;

    /**
     * 发送得频道id
     */
    Long channelId;

    public UserSendMsgEvent(Object source) {
        super(source);
    }
}
