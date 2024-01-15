package com.momoc.multi.chat.room.common.model.channel;

import com.momoc.multi.chat.room.common.model.BasePojo;
import lombok.Data;

/**
 * 频道消息记录
 * @author momoc
 * @version 1.0
 * @className ChannelMessageRecord
 * @description
 * @date 2023/7/21 17:50
 */
@Deprecated
@Data
public class ChannelMessageRecordPojo extends BasePojo {

    Long userid;

    /**
     * 消息类型：0 文本 1 图片（就是个链接） 2 语音
     * 为什么有语音呢？ 后续有打算接入语音类型消息，语音可能用其他的表做记录.语音可能需要上对象存储
     * 一些思路：将二进制信息转换成二进制，使用压缩算法进行入库，尽可能减小语音体积
     */
    Integer type;

    String content;

}
