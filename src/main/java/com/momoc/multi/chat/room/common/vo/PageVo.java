package com.momoc.multi.chat.room.common.vo;

import lombok.Data;

/**
 * @author momoc
 * @version 1.0
 * @className PageVo
 * @description
 * @date 2023/7/20 16:22
 */
@Data
public class PageVo<T> {
    Integer page;
    Integer pageSize;

    T items;

}
