package com.momoc.multi.chat.room.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.momoc.multi.chat.room.common.model.BasePojo;

/**
 * @author momoc
 * @version 1.0
 * @className IBaseService
 * @description
 * @date 2023/6/27 17:04
 */
public interface IBaseService<T extends BasePojo> extends IService<T> {
}
