package com.chatapp.logservice.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class LogMessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(LogMessageConsumer.class);

    @RabbitListener(queues = "chat-logs")
    public void writeToLogs(String message) {
        logger.info(message);
    }
}
