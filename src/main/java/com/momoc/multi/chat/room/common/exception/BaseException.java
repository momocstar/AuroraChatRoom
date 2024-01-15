package com.momoc.multi.chat.room.common.exception;


import com.momoc.multi.chat.room.common.utils.AssertUtil;

/**
 * @author momoc
 */
public class BaseException extends Throwable{
    private static final long serialVersionUID = 6937304029901546157L;
    private String errMsg;
    private Integer errCode ;
    private String bussinessMsg;

    public BaseException() {
        this.initCause((Throwable)null);
    }

    public BaseException(Throwable exception) {
        super(exception);
    }

    public BaseException(String msg, Throwable exception) {
        super(msg, exception);
        this.errMsg = msg;
    }

    public BaseException(Integer code, String msg, Throwable exception) {
        super(msg, exception);
        this.errMsg = msg;
        this.errCode = code;
    }

    public BaseException(Integer code, String msg, String bussinessMsg, Throwable exception) {
        super(msg, exception);
        this.errMsg = msg;
        this.errCode = code;
        this.bussinessMsg = bussinessMsg;
    }

    public BaseException(Integer code, String msg) {
        super(msg);
        this.errMsg = msg;
        this.errCode = code;
    }

    public BaseException(Integer code, String msg, String bussinessMsg) {
        super(msg);
        this.errMsg = msg;
        this.errCode = code;
        this.bussinessMsg = bussinessMsg;
    }

    public BaseException(int code, String msg) {
        super(msg);
        this.errMsg = msg;
        this.errCode = code;
    }

    public BaseException(int code, String msg, String bussinessMsg) {
        super(msg);
        this.errMsg = msg;
        this.errCode = code;
        this.bussinessMsg = bussinessMsg;
    }

    public BaseException(String msg) {
        super(msg);
        this.errMsg = msg;
    }

    @Override
    public String getMessage() {
        return AssertUtil.isEmpty(this.errMsg) ? super.getMessage() : this.errMsg;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public Integer getErrCode() {
        return this.errCode;
    }

    public String getBussinessMsg() {
        return this.bussinessMsg;
    }

    public void setBussinessMsg(String bussinessMsg) {
        this.bussinessMsg = bussinessMsg;
    }
}
