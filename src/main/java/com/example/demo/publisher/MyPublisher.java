package com.example.demo.publisher;

import jakarta.jms.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MyPublisher {

    private static final Logger logger =
            LoggerFactory.getLogger(MyPublisher.class);

    @Autowired
    ConnectionFactory connectionFactory;

    @Autowired
    JmsTemplate jmsTemplate;

    @Transactional
    public void publishMessage(String message) {
        logger.info("sending Message to the queue" + message);
        jmsTemplate.convertAndSend("TEST", message);
        logger.info("sending Message completed");
    }

}
