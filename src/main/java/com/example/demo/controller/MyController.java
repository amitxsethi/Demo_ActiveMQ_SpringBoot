package com.example.demo.controller;

import com.example.demo.publisher.MyPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class MyController {

    @Autowired
    MyPublisher myPublisher;

    @PostMapping("/sendMsg")
    public void postMessage(@RequestBody String message) {
        myPublisher.publishMessage(message);
    }

}
