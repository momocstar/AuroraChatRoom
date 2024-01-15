package com.momoc.multi.chat.room.common.vo;

import lombok.Data;

/**
 * 用户频道信息
 * @author momoc
 * @version 1.0
 * @className UserChannelInfoVO
 * @description
 * @date 2023/8/1 18:26
 */
@Data
public class UserChannelInfoVO {
    String nickname;

    Long userid;

    String headPic;

    Integer score;

    /**
     * 用户频道等级
     */
    Integer level;

    /**
     * 用户频道头衔，空着显示等级的头衔，
     */
    String rankName;


    /**
     * 是否在线
     */
    boolean online;

}
