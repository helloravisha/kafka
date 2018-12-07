package com.sjsu.specialtopics.kafka.consumer.listener;

import com.sjsu.specialtopics.kafka.consumer.model.User;
import com.sjsu.specialtopics.kafka.consumer.util.FirebaseSdkJersey;
import com.sun.jersey.api.client.Client;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test", group = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        Client client = Client.create();
        FirebaseSdkJersey sdk = new FirebaseSdkJersey("https://starbucks-service.firebaseio.com/", "MfS1myB9wWfDZmWF6yGQZ8u9BzCZJSTkhknHfMTO", client);

        try {
            sdk.setValue("users",message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Consumed  Message: " + message);
    }



    @KafkaListener(topics = "Kafka_Example", group = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(User user) {


    }

}
