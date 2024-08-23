package com.example.demo.config;

import java.util.concurrent.Executor;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.ExceptionListener;
import jakarta.jms.JMSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ErrorHandler;

@Configuration
public class ListenerConfig {

    private static final Logger logger =
            LoggerFactory.getLogger(ListenerConfig.class);

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(
            ConnectionFactory connectionFactory,
            DefaultJmsListenerContainerFactoryConfigurer configurer )
    {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setTaskExecutor(this.taskExecuter());
        factory.setErrorHandler(this.errorHandler());
        factory.setExceptionListener(this.exceptionListener());
        factory.setSessionAcknowledgeMode(1);
        factory.setSessionTransacted(false);
        factory.setConcurrency("3-6");
        factory.setConnectionFactory(connectionFactory);

        configurer.configure( factory, connectionFactory );
        return factory;
    }

    @Bean
    public ErrorHandler errorHandler() {
        return new ErrorHandler() {
            @Override
            public void handleError(Throwable t) {
                logger.error("Error occured :(", t);
            }
        };
    }

    @Bean
    public ExceptionListener exceptionListener() {
        return new ExceptionListener() {
            @Override
            public void onException(JMSException e) {
                logger.error("Exception occured :(", e);
            }
        };
    }

    @Bean("listenerTaskExecutor")
    public Executor taskExecuter() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("listener-");
        threadPoolTaskExecutor.setCorePoolSize(3);
        threadPoolTaskExecutor.setMaxPoolSize(6);
        return threadPoolTaskExecutor;
    }

}
