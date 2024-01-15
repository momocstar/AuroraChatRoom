package com.momoc.multi.chat.room.common.model.channel;

import com.baomidou.mybatisplus.annotation.TableName;
import com.momoc.multi.chat.room.common.model.BasePojo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author momoc
 * @version 1.0
 * @className ChannelHistoryMessage
 * @description
 * @date 2023/11/7 16:12
 */

@Accessors(chain = true)
@Data
@TableName("channel_history_message")
public class ChannelHistoryMessagePojo extends BasePojo {

    Long userid;

    Long channelId;
    Integer type;
    String content;


}
