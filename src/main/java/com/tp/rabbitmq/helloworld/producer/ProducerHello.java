package com.tp.rabbitmq.helloworld.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Created on 2021/10/23
 *
 * @author Patric Tian
 */
public class ProducerHello {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1. 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2. 设置参数
        factory.setHost("1.116.90.3");
        factory.setPort(5672);
        factory.setVirtualHost("/vmtp");
        factory.setUsername("tianpeng");
        factory.setPassword("tianpeng");
        //3. 创建连接 Connection
        Connection connection = factory.newConnection();
        //4. 创建Channel
        Channel channel = connection.createChannel();
        //5. 创建队列Queue
        channel.queueDeclare("hello_world", true, false, false, null);
        //6. 发送消息
        String body = "hello rocketmq~~";
        channel.basicPublish("", "hello_world", null, body.getBytes());
        //7. 释放资源
        channel.close();
        connection.close();

    }
}
