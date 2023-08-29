package com.bezkoder.spring.jwt.mongodb.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Job {

    private final Logger log = LoggerFactory.getLogger(Job.class);

    private static int countJobRun = 1;

    @Scheduled(fixedDelay = 100000)
    public void jobShowLog(){
        log.info("Show log run job: {}", countJobRun++);
    }
}
