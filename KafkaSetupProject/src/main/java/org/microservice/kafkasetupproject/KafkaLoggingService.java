package org.microservice.kafkasetupproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KafkaLoggingService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaLoggingService.class);

    public void logMessage(String message) {
        logger.info("Consumed message: {}", message);
        logger.info("I'm here again" + "🐶", message);
    }
}
