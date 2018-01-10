package com.xzl.cloud.workflow.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserSetup {
    String user;
    String manager;

    public Map toMap() {
        if (user == null && manager == null)
            return null;
        Map map = new HashMap();
        if (user != null)
            map.put("user", user);

        if (manager != null)
            map.put("manager", manager);
        return map;
    }
}
