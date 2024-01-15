package com.momoc.multi.chat.room.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.momoc.multi.chat.room.common.mapper.AppUserMapper;
import com.momoc.multi.chat.room.common.model.AppUserPojo;
import com.momoc.multi.chat.room.common.proto.ChannelMessage;
import com.momoc.multi.chat.room.common.proto.UserMessage;
import com.momoc.multi.chat.room.common.request.UserRegisterRequest;
import com.momoc.multi.chat.room.common.vo.AppUserInfo;
import com.momoc.multi.chat.room.core.event.user.UserSendMsgEvent;
import com.momoc.multi.chat.room.core.service.AbstractServiceImpl;
import com.momoc.multi.chat.room.core.service.IAppUserService;
import com.momoc.multi.chat.room.core.session.ChannelManagerContext;
import com.momoc.multi.chat.room.core.session.SessionManagerContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author momoc
 * @version 1.0
 * @className AppUserServiceImpl
 * @description
 * @date 2023/6/27 17:00
 */
@RequiredArgsConstructor
@Service("appUserService")
public class AppUserServiceImpl extends AbstractServiceImpl<AppUserMapper, AppUserPojo> implements IAppUserService {



    final SessionManagerContext sessionManagerContext;
    final ChannelManagerContext channelManagerContext;


    @Override
    public boolean isEmailExist(String email) {
        AppUserMapper baseMapper = getBaseMapper();
        return baseMapper.exists(new LambdaQueryChainWrapper<AppUserPojo>(baseMapper).eq(AppUserPojo::getEmail, email));
    }

    @Override
    public AppUserPojo getUserInfoByEmail(String email) {
        AppUserMapper userMapper = getBaseMapper();
        LambdaQueryWrapper<AppUserPojo> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(AppUserPojo::getEmail,email);
        AppUserPojo userPojo = userMapper.selectOne(lambdaQueryWrapper);
        return userPojo;
    }

    @Override
    public Long registerUser(UserRegisterRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        AppUserPojo appUserPojo = new AppUserPojo();
        appUserPojo.setEmail(email);
        appUserPojo.setPassword(password);
        baseMapper.insert(appUserPojo);
        return appUserPojo.getId();
    }
}
