package com.momoc.multi.chat.room.core.config;

import com.momoc.multi.chat.room.common.exception.BaseException;
import com.momoc.multi.chat.room.common.utils.HttpErrorCode;
import com.momoc.multi.chat.room.common.vo.ApiResultVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;

/**
 * @author momoc
 */
@Slf4j
@RestControllerAdvice
public class ErrorControllerAdvice {




//    @InitBinder
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler({HttpMessageNotReadableException.class})
//    public CommonResultVO bindException(HttpMessageNotReadableException e) {
//        CommonResultVO<Object> objectCommonResultVO = new CommonResultVO<>();
//        objectCommonResultVO.setCode(HttpErrorCode.IS_NOT_NULL.getCode());
////        objectCommonResultVO.setMsg(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
//        return objectCommonResultVO;
//    }
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {}
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BindException.class})
    public ApiResultVO bindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        ApiResultVO<Object> objectCommonResultVO = new ApiResultVO<>();
        objectCommonResultVO.setCode(HttpErrorCode.IS_NOT_NULL.getCode());
        objectCommonResultVO.setMsg(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return objectCommonResultVO;
    }
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResultVO bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        ApiResultVO<Object> objectCommonResultVO = new ApiResultVO<>();
        objectCommonResultVO.setCode(HttpErrorCode.IS_NOT_NULL.getCode());
        objectCommonResultVO.setMsg(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return objectCommonResultVO;
    }

    /**
     * 处理验证码异常
     * @return
     */
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler(VerifyFailException.class)
//    public CommonResultVO bindException(VerifyFailException e){
//        CommonResultVO<Object> objectCommonResultVO = new CommonResultVO<>();
//        objectCommonResultVO.setCode(HttpErrorCode.CODE_ERROR.getCode());
//        objectCommonResultVO.setMsg(HttpErrorCode.CODE_ERROR.getDesc());
//        return objectCommonResultVO;
//    }
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler(CodeNotExistsException.class)
//    public ApiResultVO bindException(CodeNotExistsException e){
//        ApiResultVO<Object> objectCommonResultVO = new CommonResultVO<>();
//        objectCommonResultVO.setCode(HttpErrorCode.CODE_ERROR.getCode());
//        objectCommonResultVO.setMsg(HttpErrorCode.CODE_ERROR.getDesc());
//        return objectCommonResultVO;
//    }

//    /**
//     * 处理验证码异常
//     * @return
//     */
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler(SendCodeException.class)
//    public CommonResultVO bindException(SendCodeException e){
//        CommonResultVO<Object> objectCommonResultVO = new CommonResultVO<>();
//        objectCommonResultVO.setCode(HttpErrorCode.EMAIL_ERROR.getCode());
//        objectCommonResultVO.setMsg(HttpErrorCode.EMAIL_ERROR.getDesc());
//        return objectCommonResultVO;
//    }
    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {Exception.class, BaseException.class})
    public ApiResultVO errorHandler(Throwable ex, HttpServletRequest request, HttpServletResponse response)
            throws Exception, BaseException {
        ApiResultVO<Object> objectCommonResultVO = new ApiResultVO<>();
        //判断是不是自定义异常
        if (ex.getCause()!=null
                && BaseException.class.isAssignableFrom(ex.getCause().getClass())
                || BaseException.class.isAssignableFrom(ex.getClass())){
            response.setStatus(200);
            objectCommonResultVO.setCode(((BaseException) ex.getCause()).getErrCode());
            objectCommonResultVO.setMsg(((BaseException) ex.getCause()).getErrMsg());
        }
        else if (ex instanceof MissingServletRequestParameterException){
            response.setStatus(200);

            objectCommonResultVO.setCode(HttpErrorCode.IS_NOT_NULL.getCode());
            objectCommonResultVO.setMsg(((MissingServletRequestParameterException) ex).getParameterName() + HttpErrorCode.IS_NOT_NULL.getDesc());
        }
        else if(ex instanceof NoHandlerFoundException){
            response.setStatus(404);
            objectCommonResultVO.setCode(HttpErrorCode.IS404.getCode());
            objectCommonResultVO.setMsg(HttpErrorCode.IS404.getDesc());
        }
        else{
            response.setStatus(500);
            objectCommonResultVO.setCode(HttpErrorCode.SYSTEM_ERROR.getCode());
            objectCommonResultVO.setMsg(HttpErrorCode.SYSTEM_ERROR.getDesc());
            log.error("系统异常,url:{}", request.getRequestURI(), ex);
        }
//        if ("1".equals(ConfigConstant.getProperty("config.debug"))){
//            ex.printStackTrace();
//        }
        return objectCommonResultVO;
    }
}
