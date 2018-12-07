package com.sjsu.specialtopics.kafka.producer.controller;

import com.sjsu.specialtopics.kafka.producer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class ProducerController {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "test";

    @PostMapping ("/publish/user")
    public String post(@RequestBody User user) {

        kafkaTemplate.send(TOPIC, user);

        return "Published successfully";
    }
}
