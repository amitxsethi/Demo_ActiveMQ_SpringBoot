package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ServiceImpl implements IService{
    private static final Logger logger =
            LoggerFactory.getLogger(ServiceImpl.class);

    @Override
    public void doSomething(String message) {
        if (message.contains("990")) {
            throw new RuntimeException("can not process 990 :D");
        }
        logger.info("jms message: {}", message);
    }
}
