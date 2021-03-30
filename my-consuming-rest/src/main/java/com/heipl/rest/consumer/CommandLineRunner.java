package com.heipl.rest.consumer;

import com.heipl.rest.template.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class CommandLineRunner {

    public static Logger log = LoggerFactory.getLogger(CommandLineRunner.class);

    @Autowired
    RestTemplateService restTemplateService;

    @Bean
    public org.springframework.boot.CommandLineRunner run() throws RestClientException {
        return args -> {
            final Quote quote = restTemplateService.getNewQuote();
            log.info(quote.toString());
            log.info(quote.getActualQuote());
        };
    }
}