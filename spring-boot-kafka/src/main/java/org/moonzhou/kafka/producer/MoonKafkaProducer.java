package org.moonzhou.kafka.producer;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * kafka生产者方法
 * @author moon zhou
 */
@Component
public class MoonKafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;
 
    /**
     * 发送数据
     * @param userId 测试参数
     */
    public void send(String userId) {
        JSONObject object = new JSONObject();
        object.put("userId", userId);
        object.put("user", "moon");

        kafkaTemplate.send("moon-test-topic", object.toJSONString());
    }
}