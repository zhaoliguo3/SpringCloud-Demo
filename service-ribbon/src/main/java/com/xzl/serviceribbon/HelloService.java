package com.xzl.serviceribbon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xzl.boilerplate.common.dto.ResultResponse;
import com.xzl.boilerplate.common.dto.exception.BizCode;
import com.xzl.boilerplate.common.utils.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public ResultResponse hiService(String name, String token) throws JsonProcessingException, InterruptedException {
        String url = "http://SERVICE-HI/hiya?name="+name;
        Map body = new HashMap();
        body.put("num", 100);
        Thread.sleep(3000);
        ResultResponse result = RestTemplateUtil.getResultReponse(restTemplate,url, HttpMethod.POST, body, token);
        return result;
    }

    public ResultResponse hiError(String name, String token) {
        return ResultResponse.createFailureResponse(BizCode.UNKNOW_ERROR.code, BizCode.UNKNOW_ERROR.message, null);
    }
}
