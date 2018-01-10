package com.xzl.cloud.workflow.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class RequestQuery {
    UserSetup userSetup;
    Map processVar;
    String taskId;
    String procInsId;
    String assigness;
    String procKey;
}
