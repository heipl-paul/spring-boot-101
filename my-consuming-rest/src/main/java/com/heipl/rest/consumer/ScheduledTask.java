package com.heipl.rest.consumer;

import com.heipl.rest.template.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Obtains new {@link Quote} at fixed interval
 */
@Component
public class ScheduledTask {
    public static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    private RestTemplateService restTemplateService;

    @Scheduled(fixedRate = 15_000)
    public void printQuoteAtFixedRate() {
        final Quote quote = restTemplateService.getNewQuote();
        LOGGER.info(quote.toString());
        LOGGER.info("\n" + quote.getActualQuote());
    }

}