package helloworld;

import com.rabbitmq.client.*;
import com.sun.nio.sctp.MessageInfo;
import org.apache.commons.lang.SerializationUtils;
import sun.nio.cs.StandardCharsets;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Create By Ray 2017/4/25 13:17
 */
public class Recv {

    private final static String QUEUE_NAME = "queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //打开连接和创建频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明队列，主要是为了防止消息接受者先运行此程序，队列还不存在时创建队列
        channel.queueDeclare(QUEUE_NAME,false, false, false, null);
        System.out.println("Waiting for message");

        //创建队列消费者
//        channel.basicConsume(QUEUE_NAME, new Consumer() {
//            //当消费者注册完成自动调用
//            public void handleConsumeOk(String consumerTag) {
//                System.out.println("handleConsumeOk " + consumerTag);
//            }
//
//            public void handleCancelOk(String consumerTag) {}
//
//            public void handleCancel(String consumerTag) throws IOException {}
//
//            public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {}
//
//            public void handleRecoverOk(String consumerTag) {}
//
//            //当消费者接收到消息会自动调用
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
////                MessageInfo messageInfo = (MessageInfo) SerializationUtils.deserialize(body);
//                System.out.println("handleDelivery ");
//                System.out.println(new String(body, "UTF-8"));
//            }
//        });
        channel.basicConsume(QUEUE_NAME,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("handleDelivery ");
                System.out.println(new String(body, "UTF-8"));
            }
        });

        while (true){}

    }

}
