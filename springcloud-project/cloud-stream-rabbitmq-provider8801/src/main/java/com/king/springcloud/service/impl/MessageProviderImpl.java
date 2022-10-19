package com.king.springcloud.service.impl;

import com.king.springcloud.service.MessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(value = Source.class) // 可以理解为一个消息发送通道（输出）的定义
public class MessageProviderImpl implements MessageProvider {

    @Resource
    private MessageChannel output;

    @Override
    public String sendMessage() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build()); // 通过消息通道构建一条消息并发送
        System.out.println("本次发送消息ID：" + serial);
        return serial;
    }
}
