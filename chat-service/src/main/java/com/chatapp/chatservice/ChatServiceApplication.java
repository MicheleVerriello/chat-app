package com.chatapp.chatservice;

import com.chatapp.chatservice.rabbitmq.Receiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatServiceApplication {

    static final String topicExchangeName = "chat-app-exchange";
    static final String queueName = "chat";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    /**
     * In this case, we use a topic exchange, and the queue is bound with a routing key of chat.message.#,
     * which means that any messages sent with a routing key that begins with "chat.message." are routed to the queue.
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("chat.message.#");
    }

    /**
     * The bean defined in the listenerAdapter() method is registered as a message listener
     * in the container (defined in container()).
     * It listens for messages on the "chat" queue. Because the Receiver class is a POJO,
     * it needs to be wrapped in the MessageListenerAdapter,
     * where you specify that it invokes receiveMessage.
     */
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(listenerAdapter);
        container.setQueueNames(queueName);

        return container;
    }

    public static void main(String[] args) {
        SpringApplication.run(ChatServiceApplication.class, args);
    }

}
