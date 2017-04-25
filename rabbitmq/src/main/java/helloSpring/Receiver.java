package helloSpring;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Create by Ray on 2017/4/25 14:37
 */
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message){
        System.out.println("Received <" + message + ">");
    }

    public CountDownLatch getLatch(){
        return latch;
    }

}
