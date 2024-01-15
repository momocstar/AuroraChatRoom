package com.momoc.multi.chat.room.common.exception;

/**
 * @author momoc
 */
public class NonePrintException extends BaseException {
    public NonePrintException(Integer code, String msg) {
        super(code, msg);
    }

    public NonePrintException(Integer code, String msg, String bussinessMsg) {
        super(code, msg, bussinessMsg);
    }

    public NonePrintException(int code, String msg) {
        super(code, msg);
    }

    public NonePrintException(int code, String msg, String bussinessMsg) {
        super(code, msg, bussinessMsg);
    }

    public NonePrintException(Integer code, String msg, Throwable exception) {
        super(code, msg, exception);
    }

    public NonePrintException(Integer code, String msg, String bussinessMsg, Throwable exception) {
        super(code, msg, bussinessMsg, exception);
    }
}
