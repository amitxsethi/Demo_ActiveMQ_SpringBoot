package com.example.demo.listener;

import com.example.demo.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MyListener {

    private static final Logger logger =
            LoggerFactory.getLogger(MyListener.class);

    @Autowired
    IService service;

    @JmsListener( destination = "TEST")
    @Transactional
    public void receiveMessage( String message )
    {
        service.doSomething(message);
        logger.info("Processing completed for " + message);
    }
}
