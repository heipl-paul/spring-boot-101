package com.heipl.schedulingtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final String FORMAT = "HH:mm:ss";

    @Scheduled(fixedRate = 5_000)
    public void reportCurrentTime() {
        LOGGER.info("The time is now {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMAT)));
    }
}