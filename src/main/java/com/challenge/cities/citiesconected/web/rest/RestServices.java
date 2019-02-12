package com.challenge.cities.citiesconected.web.rest;

import com.challenge.cities.citiesconected.services.CitiesConnection;
import com.challenge.cities.citiesconected.util.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RestServices {

    private CitiesConnection citiesConnection;

    @Autowired
    public RestServices(CitiesConnection citiesConnection) {
        this.citiesConnection = citiesConnection;
    }

    /**
     * Service to load the cities in resource/docs/cities.txt
     * And the moment that the App start, this service is going
     * to load the file.
     * To use another file, replace resource/docs/cities.txt
     * with your own cities.txt file and call this RestApi
     * http://localhost:8080/api/generate
     */
    @PostMapping("/load")
    @PostConstruct
    public ResponseEntity<Optional<Graph>> load() {

            Optional<Graph> g = citiesConnection.generateCitiesConnection();
            return Optional.ofNullable(g)
                    .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PostMapping("/connected")
    public String connected(@RequestParam String origin, @RequestParam String destination) {

        return citiesConnection.pathBetweenCities(origin.trim().toLowerCase(), destination.trim().toLowerCase()) ? "yes" : "no";
    }

    class OrgDest {
        private String origin;
        private String destination;

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public OrgDest(String origin, String destination) {
            this.origin = origin;
            this.destination = destination;
        }
    }

//    @GetMapping("/test/{id}")
//    public Optional<String> test(@PathVariable("id") String id) {
//        if(id != null)
//            return Optional.of("ID:" + id);
//        return Optional.empty();
//    }

}
