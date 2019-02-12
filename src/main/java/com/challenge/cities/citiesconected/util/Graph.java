package com.challenge.cities.citiesconected.util;

import java.util.Map;

public class Graph {
    private Map<String, Node> cities;

    public Graph(Map<String, Node> cities) {
        this.cities = cities;
    }

    public Map<String, Node> getCities() {
        return cities;
    }

    public void setCities(Map<String, Node> cities) {
        this.cities = cities;
    }
}
