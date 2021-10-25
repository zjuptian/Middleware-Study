package com.tp.rabbitmq.helloworld.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created on 2021/10/23
 *
 * @author Patric Tian
 */
public class ConsumerHello {
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
        //6. 接收消息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("Consumer:" + consumerTag);
                System.out.println("Exchange:" + envelope.getExchange());
                System.out.println("RoutingKey:" + envelope.getRoutingKey());
                System.out.println("Properties" + properties);
                System.out.println("Body:" + new String(body));
            }
        };
        channel.basicConsume("hello_world", true, defaultConsumer);
    }
}
