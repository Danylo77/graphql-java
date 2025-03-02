package org.example.fetcher;

import graphql.schema.DataFetchingEnvironment;
import org.example.loader.CarDataLoader;
import org.example.model.Car;
import graphql.schema.DataFetcher;

public class CarByIdDataFetcher implements DataFetcher<Car> {
    private final CarDataLoader carDataLoader;

    public CarByIdDataFetcher(CarDataLoader carDataLoader) {
        this.carDataLoader = carDataLoader;
    }

    @Override
    public Car get(DataFetchingEnvironment environment) {
        String carId = environment.getArgument("id");

        return carDataLoader.getCars().stream()
                .filter(car -> car.getId().equals(carId))
                .findFirst()
                .orElse(null);
    }
}

