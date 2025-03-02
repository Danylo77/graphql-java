package org.example.fetcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetchingEnvironment;
import org.example.input.CarInput;
import org.example.loader.CarDataLoader;
import org.example.model.Car;
import graphql.schema.DataFetcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CreateCarDataFetcher implements DataFetcher<Car> {
    private final CarDataLoader carDataLoader;

    public CreateCarDataFetcher(CarDataLoader carDataLoader) {
        this.carDataLoader = carDataLoader;
    }

    @Override
    public Car get(DataFetchingEnvironment environment) {
        try {
            Map<String, Object> input = environment.getArgument("input");

            ObjectMapper objectMapper = new ObjectMapper();
            CarInput carInput = objectMapper.convertValue(input, CarInput.class);

            Car car = new Car();
            car.setBrand(carInput.getBrand());
            car.setModel(carInput.getModel());
            car.setYear(carInput.getYear());
            car.setId(UUID.randomUUID().toString());

            carDataLoader.getCars().add(car);

            return car;
        } catch (Exception e) {
            throw new RuntimeException("Error creating car", e);
        }
    }
}

