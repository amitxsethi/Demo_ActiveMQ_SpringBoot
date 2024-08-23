package com.example.demo;

import com.example.demo.config.ListenerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@Import(ListenerConfig.class)
public class SpringBootActivemqDemoApplication {

	private static final Logger logger =
			LoggerFactory.getLogger(SpringBootActivemqDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActivemqDemoApplication.class, args);
	}

}
