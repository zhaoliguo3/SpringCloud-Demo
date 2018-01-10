package com.xzl.cloud.workflow.service.impl;

import com.xzl.cloud.workflow.service.WeibaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeibaoServiceImpl implements WeibaoService {
    @Autowired
    CommonServiceImpl commonService;

    @Override
    public boolean rollBackToWeibaoForm(String taskId, String advise) {
        return commonService.rollBackToDest(taskId,"weibaoForm",advise);
    }
}
