package com.momoc.multi.chat.room.common.model.channel;

import com.baomidou.mybatisplus.annotation.TableName;
import com.momoc.multi.chat.room.common.model.BasePojo;
import lombok.Data;

/**
 * 频道
 * @author momoc
 * @version 1.0
 * @className ChannelGroupInfoPojo
 * @description
 * @date 2023/7/14 17:00
 */
@TableName("channel_info")
@Data
public class ChannelInfoPojo extends BasePojo {

    /**
     * 频道类型
     */
    Integer type;

    /**
     * 频道名称
     */
    String name;

    /**
     * 频道号
     */
    String channelCode;

    /**
     * 频道说明
     */
    String description;


    /**
     * logo图
     */
    String logo;

    /**
     * 背景图
     */
    String background;

}
