package com.xzl.boilerplate.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xzl.boilerplate.common.dto.ResultResponse;
import com.xzl.boilerplate.common.dto.exception.BizCode;
import com.xzl.boilerplate.common.dto.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RestTemplateUtil {

    public static ResultResponse getResultReponse(RestTemplate restTemplate,String url,HttpMethod httpMethod, Object body, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        ObjectMapper mapper = new ObjectMapper();

        String infra;
        try {
            infra = mapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new BizException(BizCode.JSON_CONVERT_ERROR);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(infra, headers);
        ResponseEntity<ResultResponse> result = restTemplate.exchange(url, httpMethod, requestEntity, ResultResponse.class, (Object) null);
        return result.getBody();
    }
}
