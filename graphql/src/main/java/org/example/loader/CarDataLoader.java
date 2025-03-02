package org.example.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Car;

import java.io.File;
import java.util.List;

public class CarDataLoader {
    private List<Car> cars;

    public void loadCarsFromFile(String fileName) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        cars = objectMapper.readValue(new File(fileName), objectMapper.getTypeFactory().constructCollectionType(List.class, Car.class));
    }

    public List<Car> getCars() {
        return cars;
    }
}
