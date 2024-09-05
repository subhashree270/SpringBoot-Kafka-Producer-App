package in.subha.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import in.subha.constants.AppConstants;
import in.subha.model.Order;

@Configuration
public class KafkaProducerConfig {
	
	public ProducerFactory<String, Order> producerFactory() {  //ProducerFactory is a class which gives kafka server details

		Map<String, Object> configProps = new HashMap<>();

		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.HOST); //information about kafka server
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);  // about our key
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);  //about our value

		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, Order> kafkaTemplate() {  //we are passing producerFactory obj in kafkaTemplate
		return new KafkaTemplate<>(producerFactory());  //kafkaTemplate is a predefined class given by spring which is used to send the msg to kafka
	}



}
