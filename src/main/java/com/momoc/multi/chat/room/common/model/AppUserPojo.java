package com.momoc.multi.chat.room.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author momoc
 * @version 1.0
 * @className AppUserPojo
 * @description
 * @date 2023/6/27 16:26
 */
@Data
@TableName("app_user")
public class AppUserPojo extends BasePojo {

    String email;

    String password;

    /**
     * 为什么有openid呢？
     * 打算做微信，在微信进入h5页面，直接让用户授权对应的信息，拿到对应的openid，做免密登录
     */
    String openid;

    String mobile;
    String nickname;
    String headPic;
    String PersonalSignature;



}
