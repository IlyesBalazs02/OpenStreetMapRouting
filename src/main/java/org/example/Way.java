package org.example;

import java.util.ArrayList;
import java.util.List;

public class Way {
    private long id;

    //Osm maps contain nodes and ways (plus relations), ways are collections of nodes creating a road
    private List<Node> nodes;

    public Way(long id){
        this.id = id;
        nodes = new ArrayList<>();
    }

    public long getId(){ return id; }

    public void addNode(Node node){ nodes.add(node); }

    public List<Node> getNodes() {
        return nodes;
    }
}
