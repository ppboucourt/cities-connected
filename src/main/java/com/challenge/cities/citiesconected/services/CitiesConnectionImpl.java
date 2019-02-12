package com.challenge.cities.citiesconected.services;


import com.challenge.cities.citiesconected.util.Graph;
import com.challenge.cities.citiesconected.util.Node;
import com.challenge.cities.citiesconected.util.ReadFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CitiesConnectionImpl implements CitiesConnection {

    private final ReadFile readFile;

    private Graph myCities;

    private final Logger log = LoggerFactory.getLogger(CitiesConnectionImpl.class);

    @Autowired
    public CitiesConnectionImpl(ReadFile readFile) {
        this.readFile = readFile;
    }

    /**
     * Generate the graph with the information provided at resources/docs/cities.txt
     */
    @Override
    public Optional<Graph> generateCitiesConnection() {
        try {
            List<String> data = readFile.loadFile();
            Map<String, Node> map = new HashMap<>();
            //  {Boston, Newark}
            for (String s: data) {
                //I'm populating the graph with the cities in both directions
                populateGraph(map, s.split(",")[0].trim().toLowerCase(), s.split(",")[1].trim().toLowerCase());
            }
            log.info("Cities loaded!");
            this.myCities = new Graph(map);
            return Optional.of(new Graph(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private void populateGraph(Map<String, Node> map, String origin, String destination) {
        Node originN = map.get(origin);
        Node destN = map.get(destination);

        if(originN == null)
            originN = new Node(origin);
        if(destN == null)
            destN = new Node(destination);
        map.put(origin, originN);
        map.put(destination, destN);

        if(!originN.getConnections().contains(destN))
            originN.getConnections().add(destN);
        if(!destN.getConnections().contains(originN))
            destN.getConnections().add(originN);
    }

    /**
     * @param origin
     * @param destination
     * @return true if there is a path between the cities, false in case not
     *
     * The solution is a BFS to find a path between a origin and a destination
     * (the shorter one based on BFS axiom)
     * The TimeComplexity(TC) is O(N + E) ~ O(V^2) based on the Nodes
     * In case I'll haven't use the map, I should have to create a loop
     * to find first the origin Node, and then start running the BFS.
     *
     * For that case the TC would be O(V) + O(V^2) ~ O(V^2).
     * in therms the TC is almost the same, but at my point of view
     * is more clear and maintainable.
     */
    @Override
    public boolean pathBetweenCities(String origin, String destination) {
        if(origin.equals(destination) && myCities.getCities().containsKey(origin) && myCities.getCities().containsKey(origin)) return true;
        Node node =  myCities.getCities().get(origin);
        Set<Node> set = new HashSet<>();
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        set.add(node);
        while(!q.isEmpty()) {
            Node n = q.poll();
            if(n != null) {
                for (Node c: n.getConnections()) {
                    if(c == myCities.getCities().get(destination))
                        return true;
                    else
                    if(!set.contains(c)) {
                        set.add(c);
                        q.add(c);
                    }
                }
            }
        }
        return false;
    }
}
