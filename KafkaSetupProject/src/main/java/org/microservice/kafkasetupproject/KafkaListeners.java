package org.microservice.kafkasetupproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    private static final Logger logger = LoggerFactory.getLogger(KafkaListeners.class);

    @KafkaListener(topics = "order", groupId = "order-group")
    public void consumerOrder(String orderMessage) {
        try {
            logger.info("reseived order from kafka:{} 🐒", orderMessage);

            processOrder(orderMessage);
            logger.info("Order processed successfully: {}", orderMessage);
        } catch (Exception e) {
            logger.error("Error processing order: {}", e.getMessage(), e);
        }
    }

    private void processOrder(String orderMessage) throws Exception {

        if (Math.random() > 0.8)
            throw new Exception("This error ... 🥵" + orderMessage);

        logger.debug("Processing Details :", orderMessage);
    }


} // The End of Class;
