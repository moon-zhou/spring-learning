package org.moonzhou.httputil.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.httputil.dto.Result;
import org.moonzhou.httputil.param.FileParam;
import org.moonzhou.httputil.param.TestParam;
import org.moonzhou.httputil.util.ImageUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author moon zhou
 * @description http service
 * @email ayimin1989@163.com
 * @date 2022/4/24 11:03
 **/
@Slf4j
@RestController
@RequestMapping("/http/service")
public class HttpServiceController {

    private static final String FILE_PATH = "/Users/XXX/tmp/";

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
     *
     * @return
     */
    @RequestMapping("/mapData")
    @ResponseBody
    public Map<String, Object> mapData() {
        Map<String, Object> result = new HashMap<>();

        result.put("success", true);
        result.put("data", "hello");

        return result;
    }

    /**
     * http://localhost:8081/http/service/jsonData
     *
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
     *
     * @return
     */
    @RequestMapping("/resultData")
    @ResponseBody
    public Result<String> resultData() {

        return Result.success("hello");
    }

    /**
     * http://localhost:8081/http/service/restful/param/{id}/{name}
     *
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
     *
     * @return
     */
    @GetMapping("/getParam")
    @ResponseBody
    public Result<TestParam> getParam(@RequestParam("id") Long id, @RequestParam("name") String name) {

        return Result.success(new TestParam(id, name));
    }

    /**
     * http://localhost:8081/http/service/getParam2
     *
     * @return
     */
    @GetMapping("/getParam2")
    @ResponseBody
    public Result<TestParam> getParam2(TestParam param) {

        return Result.success(param);
    }

    /**
     * http://localhost:8081/http/service/postParam
     *
     * @return
     */
    @PostMapping("/postParam")
    @ResponseBody
    public Result<TestParam> postParam(@RequestBody TestParam param) {

        return Result.success(param);
    }


    ///////////////////////////////////download file////////////////////////////////////////

    /**
     * http://localhost:8081/http/service/getImgFile?id=1.jpg
     * response 返回图片文件流，直接下载
     */
    @GetMapping("/getImgFile")
    public void getImgFile(@Validated FileParam fileParam, HttpServletRequest request, HttpServletResponse response) {
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(FILE_PATH + fileParam.getId()))) {
            byte[] bytes = new byte[1024];

            // 如果没有这两行，图片直接在浏览器打开，有了这两行，图片就回被自动下载
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileParam.getId().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            response.setContentType("application/force-download");

            // 不需要手动关闭，关闭后，如果filter等其他地方有获取response的输出流，则会无法使用
            OutputStream outputStream = response.getOutputStream();

            int i = inputStream.read(bytes);
            while (i != -1) {
                outputStream.write(bytes, 0, i);
                i = inputStream.read(bytes);
            }
        } catch (Exception e) {
            log.error("response img file stream error: ", e);
        }
    }

    /**
     * http://localhost:8081/http/service/getImgBase64?id=1.jpg
     * 返回图片的base64
     *
     * @return
     */
    @GetMapping("getImgBase64")
    public Result<String> getImgBase64(@Validated FileParam fileParam) {
        return Result.success(ImageUtil.base64Img(FILE_PATH + fileParam.getId()));
    }
}
