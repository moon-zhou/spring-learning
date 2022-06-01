package org.moonzhou.httputil.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.moonzhou.httputil.dto.Result;
import org.moonzhou.httputil.param.TestParam;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  moon zhou
 * @description http service
 * @email ayimin1989@163.com
 * @date 2022/4/24 11:03
 **/
@RestController
@RequestMapping("/http/service")
public class HttpServiceController {

    /**
     * http://localhost:8081/http/service/index
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "hello spring boot aspectJ demo project!!!";
    }

    /**
     * http://localhost:8081/http/service/mapData
     * @return
     */
    @RequestMapping("/mapData")
    @ResponseBody
    public Map<String, Object> mapData() {
        Map<String, Object>  result = new HashMap<>();

        result.put("success", true);
        result.put("data", "hello");

        return result;
    }

    /**
     * http://localhost:8081/http/service/jsonData
     * @return
     */
    @RequestMapping("/jsonData")
    @ResponseBody
    public JsonNode jsonData() throws JsonProcessingException {
        String json = "{ \"name\" : \"汉字\", \"age\" : 28, \"height\": 1.75, \"ok\": true}";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode result = objectMapper.readTree(json);

        return result;
    }

    /**
     * http://localhost:8081/http/service/resultData
     * @return
     */
    @RequestMapping("/resultData")
    @ResponseBody
    public Result<String> resultData() {

        return Result.success("hello");
    }

    /**
     * http://localhost:8081/http/service/restful/param/{id}/{name}
     * @return
     */
    @GetMapping("/restful/param/{id}/{name}")
    @ResponseBody
    public Result<Map<String, Object>> resultData(@PathVariable Long id, @PathVariable String name) {

        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("name", name);

        return Result.success(data);
    }

    /**
     * http://localhost:8081/http/service/getParam
     * @return
     */
    @GetMapping("/getParam")
    @ResponseBody
    public Result<TestParam> getParam(@RequestParam("id") Long id, @RequestParam("name") String name) {

        return Result.success(new TestParam(id, name));
    }

    /**
     * http://localhost:8081/http/service/getParam2
     * @return
     */
    @GetMapping("/getParam2")
    @ResponseBody
    public Result<TestParam> getParam2(TestParam param) {

        return Result.success(param);
    }

    /**
     * http://localhost:8081/http/service/postParam
     * @return
     */
    @PostMapping("/postParam")
    @ResponseBody
    public Result<TestParam> postParam(@RequestBody TestParam param) {

        return Result.success(param);
    }

}
