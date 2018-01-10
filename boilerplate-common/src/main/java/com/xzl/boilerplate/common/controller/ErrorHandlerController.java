package com.xzl.boilerplate.common.controller;

import com.xzl.boilerplate.common.dto.ResultResponse;
import com.xzl.boilerplate.common.dto.exception.BizException;
import com.xzl.boilerplate.common.security.JwtAuthenticationTokenFilter;
import lombok.extern.java.Log;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Log
public class ErrorHandlerController implements ErrorController {

    /**
     * 未到达controller出异常后进入该方法，交由下面的方法处理
     */
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public ResultResponse<Object> error(HttpServletRequest request,
                                        HttpServletResponse response) {
        BizException exception = (BizException) request.getAttribute(JwtAuthenticationTokenFilter.FILTER_EXCEPTION);
        if (exception == null)
            return ResultResponse.createFailureResponse(500, "unknow", null);

        return ResultResponse.createFailureResponse(exception);
    }
}
