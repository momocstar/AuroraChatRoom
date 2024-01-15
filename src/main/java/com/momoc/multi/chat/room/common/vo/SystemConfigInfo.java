package com.momoc.multi.chat.room.common.vo;

import lombok.Data;

/**
 * @author momoc
 * @version 1.0
 * @className SystemConfigInfo
 * @description
 * @date 2023/7/20 16:23
 */
@Data
public class SystemConfigInfo {
    Integer port;

    String server;
    Integer msgIdLength;


}
