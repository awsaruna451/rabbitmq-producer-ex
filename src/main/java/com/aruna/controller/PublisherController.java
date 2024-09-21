package com.aruna.controller;

import com.aruna.model.MessageModel;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rabbitmq")
public class PublisherController {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    DirectExchange directExchange;

    @PostMapping("/publish")
    public ResponseEntity<?> send(@RequestBody MessageModel messageModel) {
        template.convertAndSend(directExchange.getName(),"routing.A", messageModel);
        return ResponseEntity.ok("Message Successfully sent");
    }

}
