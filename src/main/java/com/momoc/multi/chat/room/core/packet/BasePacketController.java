package com.momoc.multi.chat.room.core.packet;

import com.momoc.multi.chat.room.common.exception.BaseException;
import com.momoc.multi.chat.room.core.session.SessionManagerContext;
import com.momoc.multi.chat.room.core.session.vo.UserSessionInfo;

/**
 * @author momoc
 * @version 1.0
 * @className BasePacketController
 * @description
 * @date 2023/9/1 15:59
 */
public abstract class BasePacketController {


    public UserSessionInfo getUserInfo() throws BaseException,Exception {
        UserSessionInfo userSessionInfo = SessionManagerContext.getUserSessionInfo();
        if (userSessionInfo == null){
            return null;
        }
        return userSessionInfo;
    }
}
