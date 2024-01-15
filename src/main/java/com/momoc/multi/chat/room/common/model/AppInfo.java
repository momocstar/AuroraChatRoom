package com.momoc.multi.chat.room.common.model;

import lombok.Data;

/**
 * 一些app的配置信息
 * @author momoc
 * @version 1.0
 * @className AppInfo
 * @description
 * @date 2023/7/14 17:21
 */
@Data
public class AppInfo extends BasePojo {

    /**
     * 公众号id
     */
    String wxAppid;

    /**
     * 私钥
     */
    String privateKey;
    /**
     *  公钥
     */
    String PublicKey;




}
