package com.momoc.multi.chat.room.common.model.channel;

import com.momoc.multi.chat.room.common.model.BasePojo;
import lombok.Data;

/**
 * 频道头衔
 * 特殊的只能由频道群主赋予
 * @author momoc
 * @version 1.0
 * @className ChannelUserRank
 * @description
 * @date 2023/7/21 17:29
 */
@Data
public class ChannelUserRankPojo  extends BasePojo {

    Long channelId;
    Long userid;

    String name;

}
