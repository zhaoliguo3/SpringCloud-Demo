package com.xzl.boilerplate.common.dto;


import com.xzl.boilerplate.common.dto.exception.BizCode;
import com.xzl.boilerplate.common.dto.exception.BizException;

import java.io.Serializable;

/**
 * 标准接口返回数据体
 * 推荐用法：直接ApiResponse.createSuccessResponse() / createFailureResponse(code,msg,data)
 */
public class ResultResponse<DATA> implements Serializable{
    private int code;
    private DATA data;
    private String message;
    private static final int SUCCESS_CODE = 200;
    public static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    public static final String DEFAULT_ERROR_MESSAGE = "UNKNOWN ERROR";

    public ResultResponse(){

    }

    public static <DATA> ResultResponse createSuccessResponse(DATA data){
        return new ResultResponse<>().setSuccess(data);
    }

    public static <DATA> ResultResponse<DATA> createFailureResponse(int code, String msg,DATA data) {
        return new ResultResponse<>().setError(code,msg,data);
    }

    public static <DATA> ResultResponse<DATA> createFailureResponse(BizException bizException) {
        return new ResultResponse<>().setError(bizException);
    }
    public static <DATA> ResultResponse<DATA> createFailureResponse(BizCode bizCode) {
        return new ResultResponse<>().setError(bizCode);
    }

    public ResultResponse<DATA> setSuccess(DATA data){
        code = SUCCESS_CODE;
        message = DEFAULT_SUCCESS_MESSAGE;
        this.data = data;
        return this;
    }

    public ResultResponse setError(int code, String msg, DATA data) {
        this.code=code;
        this.message=msg;
        this.data=data;
        return this;
    }

    public ResultResponse setError(BizException ex) {
        code = ex.getCode();
        message = ex.getMsg();
        data = null;
        return this;
    }

    public ResultResponse setError(BizCode bizCode) {
        code = bizCode.code;
        message = bizCode.message;
        data = null;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DATA getData() {
        return data;
    }

    public void setData(DATA data) {
        this.data = data;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }
}
