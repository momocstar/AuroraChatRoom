package com.momoc.multi.chat.room.common.vo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.momoc.multi.chat.room.common.model.AppUserPojo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author momoc
 * @version 1.0
 * @className AppUserInfo
 * @description
 * @date 2023/9/1 16:05
 */
@Data
public class AppUserInfo {

    @JsonSerialize(using = ToStringSerializer.class)
    Long id;
    String idStr;


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


    /**
     * websocket 的token
     */
    String tokenWs;


    String sessionToken;
    public static AppUserInfo pojoToInfo(AppUserPojo appUserPojo){
        AppUserInfo appUserInfo = new AppUserInfo();
        BeanUtils.copyProperties(appUserPojo, appUserInfo);
        return appUserInfo;
    }

    public void setId(Long id) {
        this.id = id;
        this.idStr = String.valueOf(id);
    }
}
