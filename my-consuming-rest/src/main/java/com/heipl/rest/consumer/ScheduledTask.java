package com.heipl.rest.consumer;

import com.heipl.rest.template.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    public static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    private RestTemplateService restTemplateService;

    @Scheduled(fixedRate = 15_000)
    public void printQuoteAtFixedRate() {
        final Quote quote = restTemplateService.getNewQuote();
        log.info(quote.toString());
        log.info("\n" + quote.getActualQuote());
    }

}