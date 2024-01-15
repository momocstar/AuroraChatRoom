package com.momoc.multi.chat.room.common.utils;


import com.momoc.multi.chat.room.common.exception.NonePrintException;

/**
 * @author momoc
 */
public enum HttpErrorCode {

    IS_NOT_NULL(1001, "不能为空"),
    DEFALUT_PAGESIZE(1002, "页数不能超过200"),
    IS404(1003,"页面不存在"),
    SYSTEM_ERROR(1004,"系统异常"),
    MOBILE_EXIST(1005,"手机号已被注册！"),
    MOBILE_NOT_EXIST(1006,"手机号或用户未注册"),
    UN_LOGIN(1007,"您未登陆"),
    LOGIN_TIME_OUT(1008,"您的登录已过期"),
    CATALOG_DEL(1009,"目录不存在或已被删除"),
    FILE_DEL(1010,"文件不存在或已被删除"),
    CATALOG_HAS(1011,"存在下级目录，无法删除，请先还原！先彻底删除下级目录"),
    FILE_HAS(1012,"勾选的目录有文件，无法删除，请先彻底删文件。若目录不存在，那么存放在回收站中。"),
    USER_NOT_EXISTS(1013,"用户不存在"),
    USER_EXISTS(1014,"手机号已被注册"),
    NOT_MGR(1015,"您不是管理员"),
    PASSWORD_MGR_ERROR(1016,"密码不一致"),
    CODE_ERROR(1017,"验证码错误或已过期"),
    EMAIL_ERROR(1018,"邮箱错误"),
    SMS_ERROR(1019,"短信发送失败请检查手机号或联系管理员。"),
    ACCOUNT_DISABLED(1020,"账号已被禁用！"),
    SMS_SEND_ERROR(1021,"请等待一分钟后发送！"),
    MAIL_EXIST(1022,"邮箱已被绑定，请更换邮箱！"),
    PASSWORD_ERROR(1023,"密码错误！"),
    MAIL_NOT_EXIST(1024,"邮箱未被绑定！"),
    FILE_IS_UPLOADING(1025,"文件已有用户正在上传，本次上传终止！"),
    NOT_REMOVE_TO_SELF(1026,"请不要将文件夹移动到选中的文件夹或其子文件夹"),

    ACCOUNT_OR_PASSWORD_ERROR(1027,"账号或密码错误"),

    QUARTZ_UPDATE_ERROR(2001,"定时任务更新或启动失败，请检查cron表达式"),
    QUARTZ_DEL(2002,"定时任务已被删除"),
    QUARTZ_START_ERROR(2003,"定时任务启动失败=>"),
    QUARTZ_NOT_RUN(2004,"定时任务未运行"),
    PACKAGE_NOT(3001,"文件不存在"),
    ZIP_SUPPORT(3002,"目前支持zip压缩包哦"),
    ZIP_BAD(3003,"压缩包数据异常，请检查压缩包是否损坏。"),
    ZIP_ADD_OR_DEL(3004,"操作数据不能为空"),
    ZIP_ADD_NOT_MATCHING(3005,"新增文件或文件夹与压缩包路径不匹配"),
    COMPRESSOR_TASK_DEL(3006,"压缩包任务已被删除"),
    COMPRESSOR_TASK_DOING(3007,"任务正在执行中。。。。"),
    COMPRESSOR_FILE_REPEAT(3008,"文件重复！"),
    COMPRESSOR_CATALOG_REPEAT(3009,"目录重复！"),
    AFTER_UPDATE_ZIP_CONTENT_NOT(3010,"更新后的压缩包为空！"),
    ZIP_OPERATION_CONTENT_NOT(3011,"更新压缩包操作内容为空！"),
    FILE_AND_CATALOG_DEL(4017,"文件或目录不存在"),
    SHARE_NOT_EXISTS_OR_EXP(4018,"分享不存在或已过期"),
    SHARE_TOKEN_ERROR(4019,"提取码错误！"),
    SHARE_FILE_DEL(4020,"文件或目录已被删除！"),
    SHARE_NOT_YOU(4021,"该分享内容不是你的！"),
    CURRENT_SHARE_NOT(4022,"当前分享的内容，不存在这个文件！"),
    ;



    /**
     * The Code.
     */
    private Integer code;
    /**
     * The Desc.
     */
    private String desc;

    /**
     * Instantiates a new Error code desc.
     * 构造函数，枚举类别只能为私有
     * @param _nCode the n code
     * @param _nDesc the n desc
     */
    private HttpErrorCode(Integer _nCode, String _nDesc) {
        this.code = _nCode;
        this.desc = _nDesc;
    }

    /**
     * Gets code.
     *
     * @return code code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code 要设置的 code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Gets desc.
     *
     * @return desc desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets desc.
     *
     * @param desc 要设置的 desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public NonePrintException newNonePrintException(){
        return new NonePrintException(this.getCode(), this.getDesc());
    }

}
