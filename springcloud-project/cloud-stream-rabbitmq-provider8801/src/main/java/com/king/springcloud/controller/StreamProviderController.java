package com.king.springcloud.controller;

import com.king.springcloud.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/streamProvider")
public class StreamProviderController {

    @Autowired
    private MessageProvider messageProvider;

    @GetMapping(value = "/send")
    public String sendMessage() {
        return messageProvider.sendMessage();
    }
}
