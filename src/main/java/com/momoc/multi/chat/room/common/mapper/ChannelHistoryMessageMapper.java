package com.momoc.multi.chat.room.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.momoc.multi.chat.room.common.model.channel.ChannelHistoryMessagePojo;
import com.momoc.multi.chat.room.common.utils.Pager;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author momoc
 * @version 1.0
 * @className ChannelInfoMapper
 * @description
 * @date 2023/7/21 17:55
 */
@Mapper
public interface ChannelHistoryMessageMapper extends BaseMapper<ChannelHistoryMessagePojo> {

    Pager<ChannelHistoryMessagePojo> selectChannelHistoryMessageByPager(Long channelId, Long messageMaxId, Pager myPage);
}
