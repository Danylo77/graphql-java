package org.example.fetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.example.loader.CarDataLoader;
import org.example.model.Car;

import java.util.List;

public class CarDataFetcher implements DataFetcher<List<Car>> {
    private final CarDataLoader carDataLoader;

    public CarDataFetcher(CarDataLoader carDataLoader) {
        this.carDataLoader = carDataLoader;
    }

    @Override
    public List<Car> get(DataFetchingEnvironment environment) {
        return carDataLoader.getCars();
    }
}
