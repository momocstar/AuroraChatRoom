package com.momoc.multi.chat.room;

import com.momoc.multi.chat.room.common.model.AppUserPojo;
import com.momoc.multi.chat.room.core.service.IAppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppRunTests {

    @Autowired
    IAppUserService appUserService;

//    @Test
//    void contextLoads() {
//        AppUserPojo appUserPojo = new AppUserPojo();
//        appUserPojo.setOpenid("test");
//        appUserPojo.setHeadPic("test");
//        appUserService.save(appUserPojo);
//    }

}
