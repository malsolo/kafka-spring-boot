package com.malsolo.kafka.springboot;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import reactor.core.publisher.Flux;

@SpringBootApplication
@ConfigurationPropertiesScan
@Slf4j
public class KafkaSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSpringBootApplication.class, args);
	}

	@Bean
	@ConditionalOnProperty(prefix = "kafka", name = "consumer.auto-start")
	public NewTopic topic(KafkaProperties properties) {
		return TopicBuilder.name(properties.getTopic())
			.partitions(10)
			.replicas(1)
			.build();
	}

	@Bean
	public ApplicationRunner runner(MessageSender sender, KafkaProperties properties) {
		var autoStart = properties.getProducer().isAutoStart();
		var max = autoStart ? 5 : 1;
		log.warn("Producer auto start: {}. Create {} messages", autoStart, max);

		return args -> Flux.interval(Duration.ofSeconds(1))
			.filter(ignored -> autoStart)
			.map(tick -> "test message " + tick)
			.doOnNext(s -> log.warn("Generated {}", s))
			.doOnNext(sender::send)
			.take(max)
			.subscribe();
	}

}
