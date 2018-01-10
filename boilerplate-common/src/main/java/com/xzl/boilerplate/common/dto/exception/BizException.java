package com.xzl.boilerplate.common.dto.exception;

public class BizException extends RuntimeException {
    static final long serialVersionUID = -7034897190745766988L;

    private int code;
    private String msg;
    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(BizCode bizCode) {
        msg = bizCode.message;
        code = bizCode.code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toString() {
        String s = this.getClass().getName() + ", code:" + this.code + (this.msg == null ? "" : ",msg:" + this.msg);
        String message = this.getLocalizedMessage();
        return message != null ? s + ": " + message : s;
    }
}
