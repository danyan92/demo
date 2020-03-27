package com.ch.example.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Author chenhao
 * @Date 2020/3/26
 **/
@Configuration
@EnableKafka
public class KafkaConfig{
    @Bean
    @ConfigurationProperties("kafka1.kafka")
    public KafkaProperties kafkaProperties() {
        return new KafkaProperties();
    }

    public Map<String, Object> consumerProperties() {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.202.20:9092,192.168.202.21:9092,192.168.202.22:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate kafkaTemplate1(@Autowired @Qualifier("kafkaProperties") KafkaProperties kafkaProperties){
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory(kafkaProperties.buildProducerProperties()));
    }


    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory1(@Autowired @Qualifier("kafkaProperties") KafkaProperties kafkaProperties) {
        ConsumerFactory consumerFactory=new DefaultKafkaConsumerFactory(kafkaProperties.buildConsumerProperties());
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setPollTimeout(1500);
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory2(@Autowired @Qualifier("kafkaProperties") KafkaProperties kafkaProperties) {
        ConsumerFactory consumerFactory=new DefaultKafkaConsumerFactory(kafkaProperties.buildConsumerProperties());
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setPollTimeout(1500);
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<?> batchFactory(@Autowired @Qualifier("kafkaProperties") KafkaProperties kafkaProperties){

        ConsumerFactory consumerFactory=new DefaultKafkaConsumerFactory(kafkaProperties.buildConsumerProperties());
        ConcurrentKafkaListenerContainerFactory<Integer,String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(1);
        factory.getContainerProperties().setPollTimeout(1500);
        factory.setBatchListener(true);//设置为批量消费，每个批次数量在Kafka配置参数中设置
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);;//设置手动提交ackMode
        return factory;
    }





}
