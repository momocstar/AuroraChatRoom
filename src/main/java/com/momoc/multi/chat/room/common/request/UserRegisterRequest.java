package com.momoc.multi.chat.room.common.request;

import lombok.Data;

/**
 * @author momoc
 * @version 1.0
 * @className UserRegisterRequest
 * @description
 * @date 2023/8/4 18:48
 */
@Data
public class UserRegisterRequest {
    //邮箱
    String email;
    //密码
    String password;
    //验证码
    String code;


}
