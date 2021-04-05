package com.malsolo.kafka.springboot;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class KafkaSpringBootApplication {

	public static final String TOPIC = "topic1";

	public static void main(String[] args) {
		SpringApplication.run(KafkaSpringBootApplication.class, args);
	}

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name(TOPIC)
			.partitions(10)
			.replicas(1)
			.build();
	}

	@Bean
	public ApplicationRunner runner(MessageSender sender) {
		return args -> sender.send("test message");
	}

}
