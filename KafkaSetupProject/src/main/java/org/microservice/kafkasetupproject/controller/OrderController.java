package org.microservice.kafkasetupproject.controller;

import org.microservice.kafkasetupproject.model.OrderRequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class.getName());
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequestModel model) {
        try {
            logger.info("Received order request: " + model);
            kafkaTemplate.send("order", model.getOrderId(), model.toString());
            logger.info("Order {} successfully sent to Kafka topic 'orders'", model.getOrderId());
            return ResponseEntity.ok("Order successfully sent to Kafka topic 'orders'");
        } catch (Exception e) {
            logger.error("Failed to place order: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to place order.");
        }
    }
}