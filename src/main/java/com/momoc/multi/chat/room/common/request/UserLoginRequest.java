package com.momoc.multi.chat.room.common.request;

import lombok.Data;

/**
 * @author momoc
 * @version 1.0
 * @className UserLoginRequest
 * @description
 * @date 2023/8/10 19:07
 */
@Data
public class UserLoginRequest {

    //邮箱
    String email;
    //密码
    String password;

    String tokenWs;


}
