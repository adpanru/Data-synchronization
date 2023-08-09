package com.qcby.bdemo.controller;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaConsumer {


     /*   @KafkaListener(topics = {"testtopic"},groupId = "default-group")
        public void listen(ConsumerRecord<?, ?> record) {
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            if (kafkaMessage.isPresent()) {
                Object message = kafkaMessage.get();
                System.out.println("----------------- record =" + record);
                System.out.println("------------------ message =" + message);
            }
        }*/

}