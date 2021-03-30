package com.heipl.myrestservice.greeting;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Resource representation class. Maps greeting GET request
 */
@AllArgsConstructor
public class Greeting {

    @Getter private final long id;
    @Getter private final String content;

}