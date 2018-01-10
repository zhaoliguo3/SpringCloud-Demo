package com.xzl.boilerplate.common.dto.exception;

public enum BizCode {
    UNKNOW_ERROR(501, "未知错误"),
    TASK_NOT_EXIST(502,"此任务不存在"),
    ROLL_BACK_FAIL(503, "驳回失败"),
    REQUEST_PARAM_ERROR(504, "参数错误"),


    JSON_CONVERT_ERROR(601,"json格式转换错误"),

    TOKEN_EXPIRED(701,"token过期"),
    TOKEN_ILLEGAL(702, "token非法"),
    TOKEN_UNPRESENT(703,"没有传token");

    public final int code;
    public final String message;

    BizCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


}
