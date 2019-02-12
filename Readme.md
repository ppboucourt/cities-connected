# Cities Connection Challenge

The Rest Api is created with the objective to know if give tow cities there is a connection between then.
I have been provided with a file (resources/docs/cities.txt) that contain the information
about the connection between the cities.


## Development

This is a well example to represent a graph, when the vertex are the cities and the rodes are the edges.
This is my structure: 
    
 The nodes have an adjacent list with the connection.
 I have assume that if you can go from New York to Boston, 
 then you can go from Boston to New York
    
    class Node {
        String city;
        List<Node> connections
        .
        .
        .
    }
    
The class Graph use an Map structure to have O(1) access to the nodes
I have map the name(string) to the Node [Map<String, Node>].

    class Graph {
        private Map<String, Node> cities;
        .
        .
        .
     }


 
