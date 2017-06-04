package com.ray.springInActionDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Ray on 2017/6/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrackCounterConfig.class)
public class TrackCounterTest {

    //这个demo中，在TrackCounter中使用Aop，获取到了传入参数的值。

    private BlankDisc cd;

    private TrackCounter counter;

    @Autowired
    public TrackCounterTest(BlankDisc cd, TrackCounter counter) {
        this.cd = cd;
        this.counter = counter;
    }

    @Test
    public void testTrackCounter() {
        cd.play(1);
        cd.play(1);
        cd.play(2);
        cd.play(3);
    }


}
