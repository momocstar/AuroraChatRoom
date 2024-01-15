package com.momoc.multi.chat.room.common.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import lombok.Data;

/**
 * @author momoc
 * @version 1.0
 * @className BasePojo
 * @description
 * @date 2023/6/27 16:41
 */
@Data
public class BasePojo {

    @TableId(type = IdType.AUTO)
    Long id;
    @TableField(fill = FieldFill.INSERT)
    Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    Date updateTime;
}
