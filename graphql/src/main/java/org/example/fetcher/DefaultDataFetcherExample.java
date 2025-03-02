package org.example.fetcher;

import graphql.schema.*;

public class DefaultDataFetcherExample {
    public static DataFetcher<Object> getDefaultDataFetcher() {
        return environment -> "Some default value";
    }
}


