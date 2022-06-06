package org.moonzhou.httputil.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.moonzhou.httputil.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * call http service by RestTemplate
 * @author moon zhou
 */
@RestController
@RequestMapping("/http/restTemplate")
public class RestTemplateTestController {
    public static final String HTTP_SERVICE_PREFIX = "http://localhost:8081/http/service/";

    public static final String TEST_RESULT_PREFIX = "test RestTemplate: ";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * http://localhost:8081/http/restTemplate/testIndex
     *
     * @return
     */
    @RequestMapping("/testIndex")
    public String testIndex() {
        String result = restTemplate.getForObject(HTTP_SERVICE_PREFIX + "index", String.class);

        return TEST_RESULT_PREFIX + result;
    }

    /**
     * http://localhost:8081/http/restTemplate/testMapData
     * @return
     */
    @RequestMapping("/testMapData")
    @ResponseBody
    public Map<String, Object> testMapData() {

        Map result = restTemplate.getForObject(HTTP_SERVICE_PREFIX + "mapData", Map.class);


        Map<String, Object>  retResult = new HashMap<>();

        retResult.put("success", true);
        retResult.put("data", result);

        return retResult;
    }

    /**
     * http://localhost:8081/http/restTemplate/testJsonData
     * @return
     */
    @RequestMapping("/testJsonData")
    @ResponseBody
    public JsonNode testJsonData() throws JsonProcessingException {

        JsonNode result = restTemplate.getForObject(HTTP_SERVICE_PREFIX + "jsonData", JsonNode.class);
        ObjectNode objectNode = (ObjectNode) result;
        objectNode.put("add", TEST_RESULT_PREFIX + "hello");


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode retResult = objectMapper.readTree(objectNode.toString());

        return retResult;
    }

    /**
     * http://localhost:8081/http/restTemplate/testResultData
     * @return
     */
    @RequestMapping("/testResultData")
    @ResponseBody
    public Result<Object> testResultData() {

        Result result = restTemplate.getForObject(HTTP_SERVICE_PREFIX + "resultData", Result.class);

        return Result.success(result);
    }

    /**
     * http://localhost:8081/http/restTemplate/testRestfulParam
     * @return
     */
    @RequestMapping("/testRestfulParam")
    @ResponseBody
    public Result<Object> testRestfulParam() {

        Result result = restTemplate.getForObject(HTTP_SERVICE_PREFIX + "restful/param/{id}/{name}", Result.class, 1000L, "moon");

        return Result.success(result);
    }

    /**
     * http://localhost:8081/http/restTemplate/testParam
     * @return
     */
    @RequestMapping("/testParam")
    @ResponseBody
    public Result<Object> testParam() {

        Map<String, Object> moon = new HashMap<>();
        moon.put("id", 2000L);
        moon.put("name", "moon");

        Result result = restTemplate.getForObject(HTTP_SERVICE_PREFIX + "getParam?id={id}&name={name}", Result.class, moon);

        return Result.success(result);
    }

    /**
     * http://localhost:8081/http/restTemplate/testParam2
     * @return
     */
    @RequestMapping("/testParam2")
    @ResponseBody
    public Result<Object> testParam2() {

        Map<String, Object> moon = new HashMap<>();
        moon.put("id", 2000L);
        moon.put("name", "moon");

        Result result = restTemplate.getForObject(HTTP_SERVICE_PREFIX + "getParam2?id={id}&name={name}", Result.class, moon);

        // 封装对象传递未成功，此处get只能链接传参
        /*TestParam param = new TestParam(2000L, "moon");
        Result result = restTemplate.getForObject(HTTP_SERVICE_PREFIX + "getParam2?param={param}", Result.class, param);*/

        return Result.success(result);
    }

    /**
     * http://localhost:8081/http/restTemplate/testPostParam
     * @return
     */
    @RequestMapping("/testPostParam")
    @ResponseBody
    public Result<Object> testPostParam() {

        // 不加此配置，返回405
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        /*
         * postForObject 返回值为响应的数据
         * 参数1 要请求地址的url
         * 参数2 通过LinkedMultiValueMap对象封装请求参数  模拟表单参数，封装在请求体中
         * 参数3 响应数据的类型
         * @RequestBody参数接收，如果不按照此方式构造参数，返回400
         */
        Map<String, Object> request = new HashMap<>();
        request.put("id", 3000L);
        request.put("name", "moon");

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(request, httpHeaders);

        Result result = restTemplate.postForObject(HTTP_SERVICE_PREFIX + "postParam", requestEntity, Result.class);

        return result;
    }
}
