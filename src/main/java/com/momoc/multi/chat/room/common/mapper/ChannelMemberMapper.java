package com.momoc.multi.chat.room.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.momoc.multi.chat.room.common.model.channel.ChannelMemberPojo;
import com.momoc.multi.chat.room.common.vo.UserChannelInfoVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author momoc
 * @version 1.0
 * @className ChannelInfoMapper
 * @description
 * @date 2023/7/21 17:55
 */
@Mapper
public interface ChannelMemberMapper extends BaseMapper<ChannelMemberPojo> {
    List<UserChannelInfoVO> selectChannelUser(Long channelId);
}
