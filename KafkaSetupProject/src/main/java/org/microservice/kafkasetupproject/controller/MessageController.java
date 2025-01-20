package org.microservice.kafkasetupproject.controller;

import org.microservice.kafkasetupproject.KafkaLoggingService;
import org.microservice.kafkasetupproject.MessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final KafkaLoggingService kafkaLoggingService;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate, KafkaLoggingService kafkaLoggingService) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaLoggingService = kafkaLoggingService;
    }

    @PostMapping
    public void publish(@RequestBody MessageRequest request) {
        logger.info("Received message request: {}", request.message());

        kafkaTemplate.send("Tiss", request.message());
        kafkaLoggingService.logMessage("here you go");
        logger.info("Sent message to Tiss", request.message());

    }
}