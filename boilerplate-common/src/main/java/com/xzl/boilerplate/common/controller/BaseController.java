package com.xzl.boilerplate.common.controller;

import com.xzl.boilerplate.common.dto.ResultResponse;
import com.xzl.boilerplate.common.dto.exception.BizCode;
import com.xzl.boilerplate.common.dto.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class BaseController{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({Exception.class})
    public ResultResponse handleException(Exception e) {
        if (e instanceof BizException) {
            BizException bizException = (BizException) e;
            return ResultResponse.createFailureResponse(bizException.getCode(), bizException.getMsg(), null);
        } else if (e instanceof AccessDeniedException) {
            return ResultResponse.createFailureResponse(401, "没有权限", null);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("\r\n   " + e);
            StackTraceElement[] traces = e.getStackTrace();
            for (StackTraceElement trace : traces)
                sb.append("\r\n   " + trace);
            logger.info(sb.toString());
            return ResultResponse.createFailureResponse(BizCode.UNKNOW_ERROR.code, BizCode.UNKNOW_ERROR.message, sb.toString());
        }

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultResponse handleIllegalParamException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String tips = "参数不合法";
        if (errors.size() > 0) {
            tips = errors.get(0).getDefaultMessage();
        }
        return ResultResponse.createFailureResponse(BizCode.UNKNOW_ERROR.code, BizCode.UNKNOW_ERROR.message, tips);
    }
}
