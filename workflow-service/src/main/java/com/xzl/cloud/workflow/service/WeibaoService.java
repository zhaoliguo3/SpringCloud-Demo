package com.xzl.cloud.workflow.service;

public interface WeibaoService {

    /**
     * 回退到维保工单节点
     * @param taskId 任务节点ID
     * @return
     */
    boolean rollBackToWeibaoForm(String taskId,String advise);
}
