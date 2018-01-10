package com.xzl.serviceribbon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xzl.boilerplate.common.dto.ResultResponse;
import com.xzl.serviceribbon.HelloService;
import com.xzl.serviceribbon.MyHealthChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloContronller {
    @Autowired
    HelloService helloService;
    @Autowired
    MyHealthChecker myHealthChecker;
    @Autowired
    RestTemplate restTemplate;

    @Value("${server.port}")
    String port;

    @RequestMapping("/bCallA")
    public ResultResponse hi(@RequestParam String name) throws JsonProcessingException, InterruptedException {
        String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        ResultResponse resultResponse = helloService.hiService(name, token);
        return resultResponse;
    }

    @RequestMapping("/bCallF")
    public String hiF(@RequestParam String name) throws JsonProcessingException, InterruptedException {
        return restTemplate.getForObject("https://SERVICE-FEIGN/hi2?name="+name,String.class);
    }

    @RequestMapping("/hib")
    public ResultResponse hib(@RequestParam String name) throws InterruptedException {
//        Thread.sleep(4000);
        return ResultResponse.createSuccessResponse("hello " + name + " i am from port:" + port);
    }

    @RequestMapping("/up")
    public String up(@RequestParam("up") Boolean up) {
        myHealthChecker.setUp(up);
        return up.toString();
    }
}
