package com.heipl.rest.template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Optional;

/**
 * Json mapping object for Quotes. Contains an inner Json, {@link Value}
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private String type;
    private Value value;

    public String getActualQuote() {
        return Optional.ofNullable(value)
                       .map(Value::getQuote)
                       .orElse("");
    }

    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}