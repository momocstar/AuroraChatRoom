package com.momoc.multi.chat.room.common.model.channel;

import com.momoc.multi.chat.room.common.model.BasePojo;
import lombok.Data;

/**
 * 频道等级
 * @author momoc
 * @version 1.0
 * @className ChannelLevel
 * @description
 * @date 2023/7/21 17:28
 */
@Data
public class ChannelLevelPojo extends BasePojo {

    Long level;

    /**
     * 频道等级达到后显示的头衔
     */
    String name;

    /**
     * 对应频道
     */
    Long channelId;

    /**
     * 达成条件的分数
     */
    Integer score;




}
