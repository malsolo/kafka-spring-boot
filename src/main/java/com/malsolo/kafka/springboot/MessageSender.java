package com.malsolo.kafka.springboot;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class MessageSender {

    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    public void send(String msg) {
        log.info("Send message {} to {}", msg, kafkaProperties.getTopic());
        kafkaTemplate.send(kafkaProperties.getTopic(), msg);
    }
}
