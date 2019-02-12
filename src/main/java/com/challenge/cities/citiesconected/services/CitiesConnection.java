package com.challenge.cities.citiesconected.services;

import com.challenge.cities.citiesconected.util.Graph;

import java.util.Optional;

public interface CitiesConnection {

    Optional<Graph> generateCitiesConnection();

    boolean pathBetweenCities(String city1, String city2);
}
