package com.momoc.multi.chat.room.core.controller;


import com.momoc.multi.chat.room.common.exception.BaseException;
import com.momoc.multi.chat.room.common.model.AppUserPojo;
import com.momoc.multi.chat.room.common.request.UserLoginRequest;
import com.momoc.multi.chat.room.common.request.UserRegisterRequest;
import com.momoc.multi.chat.room.common.utils.AssertUtil;
import com.momoc.multi.chat.room.common.utils.ChannelConstantUtils;
import com.momoc.multi.chat.room.common.utils.HttpErrorCode;
import com.momoc.multi.chat.room.common.utils.JsonUtils;
import com.momoc.multi.chat.room.common.vo.ApiResultVO;
import com.momoc.multi.chat.room.common.vo.AppUserInfo;
import com.momoc.multi.chat.room.core.event.user.UserLoginEvent;
import com.momoc.multi.chat.room.core.event.user.UserRegisterEvent;
import com.momoc.multi.chat.room.core.service.IAppUserService;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author momoc
 * @version 1.0
 * @className AppUserController
 * @description
 * @date 2023/6/27 18:15
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController extends BaseController   {
    final IAppUserService appUserService;


    @RequestMapping("/test")
    public String test(){
        return "hello mvc";

    }


    @PostMapping("/register")
    public ApiResultVO register(
            @RequestBody UserRegisterRequest request
    ) throws BaseException {
//        String email = request.getEmail();
//        boolean emailExist = appUserService.isEmailExist(email);
//        if (emailExist){
//            return ApiResultVO.fail(HttpErrorCode.MAIL_EXIST);
//        }
//        Long userid = appUserService.registerUser(request);
//        UserRegisterEvent userRegisterEvent = new UserRegisterEvent(this);
//        userRegisterEvent.setRegisterTime(new Date());
//        userRegisterEvent.setUserid(userid);
////        userRegisterEvent.setAppUserInfo();
//        applicationEventPublisher.publishEvent(userRegisterEvent);
        return ApiResultVO.success(null);
    }
    @PostMapping("/login")
    public ApiResultVO login(
            @RequestBody UserLoginRequest request
    ) throws BaseException,Exception {
        String email = request.getEmail();
        AppUserPojo userPojo = appUserService.getUserInfoByEmail(email);

        if (userPojo == null){
            return ApiResultVO.fail(HttpErrorCode.ACCOUNT_OR_PASSWORD_ERROR);
        }
        String uuid32 = UUID.randomUUID().toString().replace("-","");
        if (!request.getPassword().equals(userPojo.getPassword())){
            return ApiResultVO.fail(HttpErrorCode.PASSWORD_ERROR);
        }
        //登录有效期12小时
        userPojo.setPassword("");
        AppUserInfo appUserInfo = AppUserInfo.pojoToInfo(userPojo);

        appUserInfo.setTokenWs(request.getTokenWs());
        redisUtils.setObject(uuid32, appUserInfo, 60 * 60 * 12);

        //推送事件
        UserLoginEvent userLoginEvent = new UserLoginEvent(this);
        userLoginEvent.setAppUserInfo(appUserInfo);
        applicationContext.publishEvent(userLoginEvent);

        return ApiResultVO.success(uuid32);
    }


    /**
     * socket中断后，已登录的用户使用此接口进行socket注册
     * @param request
     * @return
     * @throws BaseException
     * @throws Exception
     */
    @PostMapping("/registerWebsocket")
    public ApiResultVO registerWebsocket(
            @RequestBody UserLoginRequest request
    ) throws BaseException,Exception {
        AppUserPojo loginUser = getLoginUser();
        AppUserInfo appUserInfo = AppUserInfo.pojoToInfo(loginUser);

        //推送事件
        UserLoginEvent userLoginEvent = new UserLoginEvent(this);
        userLoginEvent.setAppUserInfo(appUserInfo);
        applicationContext.publishEvent(userLoginEvent);

        return ApiResultVO.success(null);
    }


    /**
     * 游客接口
     * @param id
     * @param token
     * @return
     * @throws Exception
     */

    @PostMapping("/registerGuest")
    public ApiResultVO registerGuest(Long id,  @NotNull(message = "token不能为空") String tokenWs) throws Exception {
        AppUserPojo userPojo;
        boolean newUser = false;
        if (AssertUtil.isEmpty(id)){
            userPojo = new AppUserPojo();
            String guestNumKey = ChannelConstantUtils.GUEST_NUM_KEY;
            Long increment = redisUtils.increment(guestNumKey);
            String nickName = String.format("游客%s号", increment);
            userPojo.setNickname(nickName);
            userPojo.setHeadPic("https://tupian.qqw21.com/article/UploadPic/2020-4/202041821155338249.jpg");
            appUserService.save(userPojo);
            newUser = true;

        }else{
            userPojo = appUserService.getById(id);
            if (userPojo == null){
                return registerGuest(null, tokenWs);
            }
        }
        log.info("访客注册用户:{} token:{}", JsonUtils.toJson(userPojo), tokenWs);
        AppUserInfo appUserInfo = AppUserInfo.pojoToInfo(userPojo);
        appUserInfo.setTokenWs(tokenWs);
        String uuid32 = UUID.randomUUID().toString().replace("-","");
        appUserInfo.setSessionToken(uuid32);
        if (newUser){
            UserRegisterEvent userRegisterEvent = new UserRegisterEvent(this);
            userRegisterEvent.setRegisterTime(new Date());
            userRegisterEvent.setUserid(userPojo.getId());
            userRegisterEvent.setAppUserInfo(appUserInfo);
            applicationEventPublisher.publishEvent(userRegisterEvent);
        }
        redisUtils.setObject(uuid32, appUserInfo, 60 * 60 * 12);
        //推送事件
        UserLoginEvent userLoginEvent = new UserLoginEvent(this);
        userLoginEvent.setAppUserInfo(appUserInfo);
        applicationContext.publishEvent(userLoginEvent);


        return ApiResultVO.success(appUserInfo);

    }
}
