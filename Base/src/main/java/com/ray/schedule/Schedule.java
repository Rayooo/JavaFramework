package com.ray.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 2017/5/2 19:30
 */

//@EnableScheduling
//@Component
public class Schedule {

    private static final Logger logger = LoggerFactory.getLogger(Schedule.class);

//    @Scheduled(cron = "*/5 * * * * *")
    public void task(){
        logger.info("------------------task start--------------------");

        logger.info("-------------------task end---------------------");

    }

}
