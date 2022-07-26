package org.moonzhou.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * kafka 消费者
 *
 * @author moon zhou
 */
@Component
@Slf4j
public class MoonKafkaConsumer {
    /**
     * 未指定groupId，则使用yml里配置的groupId：spring.kafka.consumer.group-id
     * @param consumerRecord
     */
    @KafkaListener(topics = {"moon-test-topic"})
    public void consumer(ConsumerRecord<?, ?> consumerRecord) {
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        log.info(">>>>>>>>>> record = {}.", kafkaMessage);

        // 判断对象是否存在
        if (kafkaMessage.isPresent()) {
            // 得到Optional实例中的值
            Object message = kafkaMessage.get();

            log.info("消费消息: {}", message);
        }
    }

    @KafkaListener(topics = {"moon-test-topic"}, groupId = "moon-consumer-group1")
    public void consumer1(ConsumerRecord<?, ?> consumerRecord) {
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        log.info(">>>>>>>>>> record1 = {}.", kafkaMessage);

        // 判断对象是否存在
        if (kafkaMessage.isPresent()) {
            // 得到Optional实例中的值
            Object message = kafkaMessage.get();

            log.info("消费消息1: {}", message);
        }
    }

    @KafkaListener(topics = {"moon-test-topic"}, groupId = "moon-consumer-group2")
    public void consumer2(ConsumerRecord<?, ?> consumerRecord) {
        // 判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        log.info(">>>>>>>>>> record2 = {}.", kafkaMessage);

        // 判断对象是否存在
        if (kafkaMessage.isPresent()) {
            // 得到Optional实例中的值
            Object message = kafkaMessage.get();

            log.info("消费消息2: {}", message);
        }
    }
}