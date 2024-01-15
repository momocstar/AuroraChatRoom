package com.momoc.multi.chat.room.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.momoc.multi.chat.room.common.mapper.ChannelHistoryMessageMapper;
import com.momoc.multi.chat.room.common.mapper.ChannelInfoMapper;
import com.momoc.multi.chat.room.common.mapper.ChannelMemberMapper;
import com.momoc.multi.chat.room.common.model.channel.ChannelHistoryMessagePojo;
import com.momoc.multi.chat.room.common.model.channel.ChannelInfoPojo;
import com.momoc.multi.chat.room.common.model.channel.ChannelMemberPojo;
import com.momoc.multi.chat.room.common.utils.ChannelConstantUtils;
import com.momoc.multi.chat.room.common.utils.Pager;
import com.momoc.multi.chat.room.common.vo.UserChannelInfoVO;
import com.momoc.multi.chat.room.core.service.AbstractServiceImpl;
import com.momoc.multi.chat.room.core.service.IChannelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author momoc
 * @version 1.0
 * @className ChannelService
 * @description
 * @date 2023/8/1 18:16
 */
@RequiredArgsConstructor
@Service
public class ChannelServiceImpl extends AbstractServiceImpl<ChannelInfoMapper, ChannelInfoPojo>  implements IChannelService {




    private final ChannelHistoryMessageMapper channelHistoryMessageMapper;
    private final ChannelMemberMapper channelMemberMapper;
    @Override
    public List<UserChannelInfoVO> getChannelAllUser(Long channelId) {
        return channelMemberMapper.selectChannelUser(channelId);
    }

    @Override
    public List<ChannelInfoPojo> getDefaultChannelList() {
        LambdaQueryWrapper<ChannelInfoPojo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChannelInfoPojo::getType, ChannelConstantUtils.default_channel_type);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<ChannelMemberPojo> findUserChannel(Long userid) {
        LambdaQueryWrapper<ChannelMemberPojo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChannelMemberPojo::getUserid, userid);
        return channelMemberMapper.selectList(wrapper);
    }

    @Override
    public void saveUserMessage(ChannelHistoryMessagePojo channelHistoryMessagePojo) {
        channelHistoryMessageMapper.insert(channelHistoryMessagePojo);
    }

    @Override
    public Pager<ChannelHistoryMessagePojo> getCHannelHistoryMessageByPager(Long channelId, Pager myPage, Long messageMaxId) {

        Pager<ChannelHistoryMessagePojo> pager = channelHistoryMessageMapper.selectChannelHistoryMessageByPager(channelId,messageMaxId,  myPage);
        return pager;
    }


}
