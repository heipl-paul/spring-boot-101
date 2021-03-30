package com.heipl.rest.consumer;

import com.heipl.rest.template.Quote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RestTemplateService {

    public static final String QUOTE_URL = "https://quoters.apps.pcfone.io/api/random";
    private final RestTemplate restTemplate;

    /**
     * Obtain new quote from {@link #QUOTE_URL}
     * @return new {@link Quote}
     */
    public Quote getNewQuote() {
        return restTemplate.getForObject(QUOTE_URL, Quote.class);
    }
}