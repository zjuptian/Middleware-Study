package com.tp.rabbitmq.workqueues.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created on 2021/10/25
 *
 * @author Patric Tian
 */
public class ProducerWorkQueues {
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
        channel.queueDeclare("work_queues", true, false, false, null);
        for (int i = 0; i < 10; i++) {
            //6. 发送消息
            String body = i + "hello rocketmq~~";
            channel.basicPublish("", "work_queues", null, body.getBytes());
        }
        //7. 释放资源
        channel.close();
        connection.close();
    }
}
