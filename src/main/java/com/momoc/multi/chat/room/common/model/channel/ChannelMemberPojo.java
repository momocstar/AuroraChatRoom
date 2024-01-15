package com.momoc.multi.chat.room.common.model.channel;

import com.baomidou.mybatisplus.annotation.TableName;
import com.momoc.multi.chat.room.common.model.BasePojo;
import lombok.Data;

/**
 * 频道成员
 * @author momoc
 * @version 1.0
 * @className ChannelGroupMember
 * @description
 * @date 2023/7/14 17:04
 */
@TableName("channel_member")
@Data
public class ChannelMemberPojo extends BasePojo {


    /**
     * 用户所在频道
     */
    Long channelId;
    Long userid;

    /**
     * 用户当前频道分数
     */
    Integer score;

    /**
     * 用户频道等级
     */
    Long levelId;

    /**
     * 用户频道头衔，空着显示等级的头衔
     */
    Long userRankId;

}
