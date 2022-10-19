package com.king.springcloud.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(value = Sink.class) // 可以理解为一个消息接收通道（输入）的定义
public class ReceiveMessageListener {

    @Value("${server.port}")
    private String port;

    @StreamListener(Sink.INPUT)
    public void receiveMessage(Message<String> message) {
        System.out.println("端口:"+port+"接收到的消息"+message.getPayload());
    }
}
