package com.heipl.relationaldataaccess;

import lombok.Data;

@Data
public class Customer {

    private final long id;
    private final String firstName, lastName;

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}