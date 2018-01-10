package com.xzl.cloud.workflow.controller;

import com.xzl.boilerplate.common.dto.ResultResponse;
import com.xzl.boilerplate.common.dto.exception.BizCode;
import com.xzl.cloud.workflow.service.WeibaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "${wf.version}/weibao")
public class WeibaoController {
    @Autowired
    WeibaoService weibaoService;

    /**
     * 驳回
     * @param map taskId 任务节点ID, advise驳回建议
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/rollBack", method = RequestMethod.POST)
    public ResultResponse rollBack(@RequestBody Map<String,String> map) throws IOException {
        boolean flag = weibaoService.rollBackToWeibaoForm(map.get("taskId"), map.get("advise"));
        if (flag)
            return ResultResponse.createSuccessResponse("ok");
        else
            return ResultResponse.createFailureResponse(BizCode.ROLL_BACK_FAIL);
    }

}
