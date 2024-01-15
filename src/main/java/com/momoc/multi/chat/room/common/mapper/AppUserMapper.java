package com.momoc.multi.chat.room.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.momoc.multi.chat.room.common.model.AppUserPojo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author momoc
 * @version 1.0
 * @className AppUserMapper
 * @description
 * @date 2023/6/27 16:50
 */
@Mapper
public interface AppUserMapper extends BaseMapper<AppUserPojo> {
}
