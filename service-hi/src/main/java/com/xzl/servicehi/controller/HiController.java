package com.xzl.servicehi.controller;

import com.xzl.boilerplate.common.controller.BaseController;
import com.xzl.boilerplate.common.dto.ResultResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HiController extends BaseController {
    @Value("${server.port}")
    String port;

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/hiya",method = RequestMethod.POST)
    public ResultResponse home(@RequestParam String name, @RequestBody Map map) {
        return ResultResponse.createSuccessResponse("hi " + name + ",i am from port:" + port + " " + map.get("num"));
    }
}
