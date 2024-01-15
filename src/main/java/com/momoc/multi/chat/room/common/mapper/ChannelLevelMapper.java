package com.momoc.multi.chat.room.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.momoc.multi.chat.room.common.model.channel.ChannelInfoPojo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author momoc
 * @version 1.0
 * @className ChannelInfoMapper
 * @description
 * @date 2023/7/21 17:55
 */
@Mapper
public interface ChannelLevelMapper extends BaseMapper<ChannelInfoPojo> {
}
