package com.malsolo.kafka.springboot;

import static com.malsolo.kafka.springboot.KafkaSpringBootApplication.TOPIC;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MessageSender {

    private final KafkaTemplate<Integer, String> kafkaTemplate;

    public void send(String msg) {
        kafkaTemplate.send(TOPIC, msg);
    }
}
