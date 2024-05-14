package org.example;
import java.util.ArrayList;
import java.util.List;

class GraphNode extends Node{
    public GraphNode(long id, double lat, double lon)
    {
        super(id,lat,lon);
    }
    private List<Edge> edges;

    public List<Edge> getNeighbours(){return edges;}
    public void addEdge(Edge newEdge){ edges.add(newEdge);}
}

class Edge{
    private GraphNode destination;
    private double weight;
}

