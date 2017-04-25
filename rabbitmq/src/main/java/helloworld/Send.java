package helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Create by Ray on 2017/4/25 13:08
 */
public class Send {

    //队列名称
    private final static String QUEUE_NAME = "queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //连接到RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();

        //设置主机名称
        factory.setHost("127.0.0.1");

        //创建一个连接
        Connection connection = factory.newConnection();

        //创建一个频道
        Channel channel = connection.createChannel();

        //指定一个队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //发送的消息
        String message = "Hello World hahaha";

        //往队列发送一条消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        System.out.println("Send " + message );

        //关闭频道和连接
        channel.close();
        connection.close();

    }


}
