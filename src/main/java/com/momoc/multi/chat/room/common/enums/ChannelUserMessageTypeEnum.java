package com.momoc.multi.chat.room.common.enums;

import lombok.Getter;

/**
 * @author momoc
 * @version 1.0
 * @className ChannelUserMessageTypeEnum
 * @description
 * @date 2023/11/7 14:22
 */
@Getter
public enum ChannelUserMessageTypeEnum {

    /**
     * 文字类型消息
     */
    TEXT_MESSAGE_TYPE(1);
    ;

    ChannelUserMessageTypeEnum(int type) {
        this.type = type;
    }

    int type;

}
