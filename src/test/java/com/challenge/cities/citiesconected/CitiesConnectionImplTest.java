package com.challenge.cities.citiesconected;

import com.challenge.cities.citiesconected.services.CitiesConnection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CitiesConnectionImplTest {

    @Autowired
    CitiesConnection citiesConnection;

    private List<List<String>> cities;

    @Test
    @Before
    public void contextLoads() {
        citiesConnection.generateCitiesConnection();
        String destination = "Kendall";
        String origin = "Naples";
        List<String> connection = new ArrayList<>();
        connection.add("Miami");
        connection.add("kendall");
        cities.add(connection);
        connection = new ArrayList<>();
        connection.add("Naples");
        connection.add("Miami");
        cities.add(connection);
        connection = new ArrayList<>();
        connection.add("Homestead");
        connection.add("Naples");
        cities.add(connection);
    }


    @Test
    public void generateCitiesConnection() {
        for (int i = 0; i < cities.size(); i++) {
            Assert.assertTrue(citiesConnection.pathBetweenCities(cities.get(i).get(0), cities.get(i).get(0)));
        }
    }
}
