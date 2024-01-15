package com.momoc.multi.chat.room.core.event.user;

import com.momoc.multi.chat.room.common.model.AppUserPojo;
import com.momoc.multi.chat.room.common.vo.AppUserInfo;
import lombok.Data;
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
public class UserLoginEvent extends ApplicationEvent {
    public UserLoginEvent(Object source) {
        super(source);
    }

    AppUserInfo appUserInfo;
}
