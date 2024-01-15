package com.momoc.multi.chat.room.core.service;

import com.momoc.multi.chat.room.common.model.channel.ChannelHistoryMessagePojo;
import com.momoc.multi.chat.room.common.model.channel.ChannelInfoPojo;
import com.momoc.multi.chat.room.common.model.channel.ChannelMemberPojo;
import com.momoc.multi.chat.room.common.utils.Pager;
import com.momoc.multi.chat.room.common.vo.UserChannelInfoVO;
import java.util.List;

/**
 * @author momoc
 * @version 1.0
 * @className IChannelService
 * @description
 * @date 2023/8/1 18:25
 */
public interface IChannelService extends IBaseService<ChannelInfoPojo> {
    List<UserChannelInfoVO> getChannelAllUser(Long channelId);

    List<ChannelInfoPojo> getDefaultChannelList();

    List<ChannelMemberPojo> findUserChannel(Long userid);


    void saveUserMessage(ChannelHistoryMessagePojo channelHistoryMessagePojo);

    Pager<ChannelHistoryMessagePojo> getCHannelHistoryMessageByPager(Long channelId, Pager myPage, Long messageMaxId);
}
