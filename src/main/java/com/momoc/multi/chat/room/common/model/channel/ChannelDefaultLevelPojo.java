package com.momoc.multi.chat.room.common.model.channel;

import com.momoc.multi.chat.room.common.model.BasePojo;
import lombok.Data;

/**
 * 频道等级制度
 * @author momoc
 * @version 1.0
 * @className ChannelDefaultLevel
 * @description
 * @date 2023/7/21 17:33
 */
@Data
public class ChannelDefaultLevelPojo extends BasePojo {


    Long level;

    /**
     * 频道等级分组
     */
    String groupName;

    String name;


    String score;
}
