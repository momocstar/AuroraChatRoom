package com.momoc.multi.chat.room.core.service;


import com.momoc.multi.chat.room.common.model.AppUserPojo;
import com.momoc.multi.chat.room.common.request.UserRegisterRequest;

import java.io.Serializable;

/**
 * @author momoc
 * @version 1.0
 * @className AppUserService
 * @description
 * @date 2023/6/27 16:52
 */
public interface IAppUserService extends IBaseService<AppUserPojo> {
    boolean isEmailExist(String email);

    AppUserPojo getUserInfoByEmail(String email);

    Long registerUser(UserRegisterRequest request);


}
