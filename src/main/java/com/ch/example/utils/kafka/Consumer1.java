package com.ch.example.utils.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: TODO
 * @Author chenhao
 * @Date 2020/3/26
 **/
@Component
public class Consumer1 {
    //@KafkaListener(topics = "xcx-msg-dev",containerFactory = "kafkaListenerContainerFactory1")
    public void get(String msg){
        System.out.println("1消费-----"+msg);
    }

    //@KafkaListener(topics = "xcx-msg-dev",containerFactory = "kafkaListenerContainerFactory2")
    public void get2(String msg){
        System.out.println("2消费-----"+msg);
    }
    @KafkaListener(topics = "xcx-msg-dev",containerFactory = "batchFactory")
    public void getBatch(List<ConsumerRecord> consumerRecord, Acknowledgment ack){
        System.out.println("batch start----");
        consumerRecord.stream().forEach(n-> {
            System.out.println("batch----"+n.value().toString()
                    +"------offset:"+n.offset()
                    +"------partition"+n.partition());
        });
        System.out.println("batch end----");
        ack.acknowledge();
    }
}
