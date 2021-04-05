package com.malsolo.kafka.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageReceiver {

    @KafkaListener(id = "MessageReceiver",
        autoStartup = "${kafka.consumer.auto-start}",
        topics = "${kafka.topic}",
        groupId = "${kafka.consumer.group-id}",
        clientIdPrefix = "MessageReceiver-Prefix"
    )
    public void processMessage(String content) {
        log.info("Message received: {}", content);
    }

}
