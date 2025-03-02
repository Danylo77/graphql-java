package org.example;

import graphql.ExecutionResult;
import graphql.GraphQL;
import org.example.loader.CarDataLoader;
import org.example.model.Car;
import org.example.service.GraphQLService;

public class GraphQLApp {
    public static void main(String[] args) {
        try {
            GraphQLService graphQLService = new GraphQLService();
            graphQLService.init();

            CarDataLoader carDataLoader = new CarDataLoader();
            carDataLoader.loadCarsFromFile("cars.json");

            String queryById = "{ getCar(id: \"1\") { id, brand, model, year } }";
            GraphQL graphQL = graphQLService.getGraphQL();
            var resultById = graphQL.execute(queryById);
            System.out.println("Get car by ID: " + resultById.getData().toString());

            String queryAllCars = "{ getAllCars { id, brand, model, year } }";
            var resultAllCars = graphQL.execute(queryAllCars);
            System.out.println("All cars: " + resultAllCars.getData().toString());

            String mutation = "mutation { createCar(input: { brand: \"Audi\", model: \"Q7\", year: 2022 }) { id, brand, model, year } }";
            var mutationResult = graphQL.execute(mutation);
            System.out.println("Create car mutation result: " + mutationResult.getData().toString());

            String queryAllCars2 = "{ getAllCars { id, brand, model, year } }";
            var resultAllCars2 = graphQL.execute(queryAllCars2);
            System.out.println("All cars: " + resultAllCars2.getData().toString());

            String queryDefaultField = "{ defaultField }";
            var resultDefaultField = graphQL.execute(queryDefaultField);

            System.out.println("Default field value: " + resultDefaultField.getData().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

