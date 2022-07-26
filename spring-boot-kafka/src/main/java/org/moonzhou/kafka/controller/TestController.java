package org.moonzhou.kafka.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.kafka.producer.MoonKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moon zhou
 * @description test kafka
 * @email ayimin1989@163.com
 * @date 2022/7/26 21:03
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MoonKafkaProducer moonKafkaProducer;

    /**
     * http://localhost:8081/test/index
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "hello spring boot file upload demo project!!!";
    }


    /**
     * test send kafka
     * http://localhost:8081/test/sendKafka/001
     *
     * @return
     */
    @GetMapping("/sendKafka/{userId}")
    public JSONObject sendKafka(@PathVariable(name = "userId") String userId) {

        moonKafkaProducer.send(userId);

        JSONObject object = new JSONObject();
        object.put("success", true);
        return object;
    }

}
