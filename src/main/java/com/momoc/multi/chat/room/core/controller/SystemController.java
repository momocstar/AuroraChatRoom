package com.momoc.multi.chat.room.core.controller;

import com.momoc.multi.chat.room.common.vo.ApiResultVO;
import com.momoc.multi.chat.room.common.vo.SystemConfigInfo;
import com.momoc.netty.frame.config.SmartNettyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author momoc
 * @version 1.0
 * @className SystemController
 * @description
 * @date 2023/7/20 16:21
 */
@RestController
@RequestMapping("/system")
public class SystemController {


    @Autowired
    SmartNettyProperties smartNettyProperties;

    /**
     * 获取系统配置信息
     * @return
     */
    @RequestMapping("/getSystemConfig")
    public ApiResultVO getSystemConfig(){
        SystemConfigInfo systemConfigInfo = new SystemConfigInfo();
        systemConfigInfo.setPort(smartNettyProperties.getPort());
        systemConfigInfo.setMsgIdLength(smartNettyProperties.getMsgIdLength());
        systemConfigInfo.setServer(smartNettyProperties.getWebsocketPath());
        return ApiResultVO.success(systemConfigInfo);
    }
}
