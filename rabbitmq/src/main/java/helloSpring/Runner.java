package helloSpring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 2017/4/25 15:07
 */
@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Receiver receiver;
    @Autowired
    private ConfigurableApplicationContext context;

//    public Runner(RabbitTemplate rabbitTemplate, Receiver receiver, ConfigurableApplicationContext context) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.receiver = receiver;
//        this.context = context;
//    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Sending Message....");
        rabbitTemplate.convertAndSend(Application.queueName, "Hello from RabbitMQ~");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        context.close();
    }
}
