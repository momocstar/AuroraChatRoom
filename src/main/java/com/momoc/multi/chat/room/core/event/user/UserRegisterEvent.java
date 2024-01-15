package com.momoc.multi.chat.room.core.event.user;

import com.momoc.multi.chat.room.common.vo.AppUserInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Date;

@Setter
@Getter
public class UserRegisterEvent extends ApplicationEvent {


    AppUserInfo appUserInfo;

    Long userid;

    Date registerTime;

    public UserRegisterEvent(Object source) {
        super(source);
    }

}
