package com.momoc.multi.chat.room.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.momoc.multi.chat.room.common.mapper.ChannelInfoMapper;
import com.momoc.multi.chat.room.common.mapper.ChannelMemberMapper;
import com.momoc.multi.chat.room.common.model.channel.ChannelInfoPojo;
import com.momoc.multi.chat.room.common.model.channel.ChannelMemberPojo;
import com.momoc.multi.chat.room.common.utils.ChannelConstantUtils;
import com.momoc.multi.chat.room.common.vo.UserChannelInfoVO;
import com.momoc.multi.chat.room.core.service.AbstractServiceImpl;
import com.momoc.multi.chat.room.core.service.IChannelMemberService;
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
public class ChannelMemberServiceImpl extends AbstractServiceImpl<ChannelMemberMapper, ChannelMemberPojo>  implements IChannelMemberService {




}
