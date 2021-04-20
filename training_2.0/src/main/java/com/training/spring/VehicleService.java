package com.training.spring;

import com.training.spring.dto.Vehicle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class VehicleService {
    private List<Vehicle> vehicles = new ArrayList<>(Arrays.asList(
            new Vehicle(1, "bh-01-abc", "red"),
            new Vehicle(2, "cj-06-abc", "red"),
            new Vehicle(3, "cj-61-abc", "blue"),
            new Vehicle(4, "tm-69-abc", "blue"),
            new Vehicle(5, "b-01-abc", "white")
    ));

    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    public void addVehicle(Vehicle v) {
        Optional.ofNullable(v).ifPresent(vehicles::add);
    }

    public void deleteVehicle(int id) {
        vehicles.removeIf(v -> v.getId() == id);
    }
}