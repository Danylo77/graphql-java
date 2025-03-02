package org.example.service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import lombok.extern.slf4j.Slf4j;
import org.example.fetcher.CarByIdDataFetcher;
import org.example.fetcher.CarDataFetcher;
import org.example.fetcher.CreateCarDataFetcher;
import org.example.fetcher.DefaultDataFetcherExample;
import org.example.loader.CarDataLoader;

import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class GraphQLService {
    private GraphQL graphQL;

    public void init() throws Exception {
        String schemaFile = new String(Files.readAllBytes(Paths.get("graphql/src/main/resources/car-schema.graphqls")));

        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeRegistry = schemaParser.parse(schemaFile);


        CarDataLoader carDataLoader = new CarDataLoader();
        carDataLoader.loadCarsFromFile("cars.json");

        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("getAllCars", new CarDataFetcher(carDataLoader))
                        .dataFetcher("getCar", new CarByIdDataFetcher(carDataLoader))
                        .dataFetcher("defaultField", DefaultDataFetcherExample.getDefaultDataFetcher())
                )
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("createCar", new CreateCarDataFetcher(carDataLoader))
                )
                .build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, wiring);

        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}

