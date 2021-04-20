package com.training.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {

    private int id;
    private String vehicleNo;
    private String color;

}