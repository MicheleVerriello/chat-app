package com.chatapp.chatservice.rabbitmq;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * The Receiver is a POJO that defines a method for receiving messages.
 * When you register it to receive messages, you can name it anything you want.
 */
@Component
public class Receiver {

    /*
        For convenience, this POJO also has a CountDownLatch.
        This lets it signal that the message has been received.
        This is something you are not likely to implement in a production application
     */
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received < " + message + " >");
        countDownLatch.countDown();
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}
