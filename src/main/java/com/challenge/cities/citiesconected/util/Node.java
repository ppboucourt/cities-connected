package com.challenge.cities.citiesconected.util;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private String city;

    private List<Node> connections;

    public Node(String city) {
        this.city = city;
        this.connections = new ArrayList<>();
    }

    public Node(String city, List<Node> connections) {
        this.city = city;
        this.connections = connections;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Node> getConnections() {
        return connections;
    }

    public void setConnections(List<Node> connections) {
        this.connections = connections;
    }

}
