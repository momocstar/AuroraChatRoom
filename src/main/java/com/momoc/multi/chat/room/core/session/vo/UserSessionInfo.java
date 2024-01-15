package com.momoc.multi.chat.room.core.session.vo;

import com.momoc.multi.chat.room.common.vo.AppUserInfo;
import io.netty.channel.Channel;
import lombok.Data;

/**
 * @author momoc
 * @version 1.0
 * @className UserSessionInfo
 * @description
 * @date 2023/7/5 18:21
 */
@Data
public class UserSessionInfo {

    Channel channel;

    AppUserInfo appUserInfo;


}
