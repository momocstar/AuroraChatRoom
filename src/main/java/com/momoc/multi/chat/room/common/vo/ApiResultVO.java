package com.momoc.multi.chat.room.common.vo;

import com.momoc.multi.chat.room.common.utils.HttpErrorCode;
import lombok.Data;

/**
 * @author momoc
 * @version 1.0
 * @className ApiResultVO
 * @description
 * @date 2023/7/20 16:21
 */
@Data
public class ApiResultVO<T> {

    String msg;
    Integer code;
    T data;


    public static <T> ApiResultVO<T> success(T data){
        ApiResultVO<T> apiResultVO = new ApiResultVO<T>();
        apiResultVO.setCode(0);
        apiResultVO.setData(data);
        return apiResultVO;
    }

    public static  ApiResultVO fail(String code,String msg){
        ApiResultVO apiResultVO = new ApiResultVO();
        apiResultVO.setCode(-1);
        return apiResultVO;
    }

    public static  ApiResultVO fail(HttpErrorCode errorCode){
        ApiResultVO apiResultVO = new ApiResultVO();
        apiResultVO.setCode(errorCode.getCode());
        apiResultVO.setMsg(errorCode.getDesc());
        return apiResultVO;
    }
}
