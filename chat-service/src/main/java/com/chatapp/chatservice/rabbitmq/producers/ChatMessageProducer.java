package com.chatapp.chatservice.rabbitmq.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public ChatMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String queue, String message) {
        rabbitTemplate.convertAndSend(queue, message);
    }
}
