package com.malsolo.kafka.springboot;

import static com.malsolo.kafka.springboot.KafkaSpringBootApplication.TOPIC;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageReceiver {

    @KafkaListener(topics = TOPIC)
    public void processMessage(String content) {
        log.info("Message received: {}", content);
    }

}
