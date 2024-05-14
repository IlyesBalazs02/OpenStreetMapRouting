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

public class Graph {
    List<Node> GraphNodeList = new ArrayList<>();


    //private List<Way> ways;
    private List<GraphNode> nodes = new ArrayList<>();

    public Graph(List<Way> tmp){
        //ways = tmp;
        Init(tmp);
    }

    
    private void Init(List<Way> ways){
        /*
        System.out.println("GRAPH:");

        for (Way way : ways){
            Node tmp = way.getNodes().get(0);
            if(!AlreadyExist(tmp.getId())) {
                nodes.add(new GraphNode(tmp.getId(), tmp.getLat(), tmp.getLon()));
            }


            tmp = way.getNodes().get(way.getNodes().size() - 1);
            if(!AlreadyExist(tmp.getId())) {
                nodes.add( new GraphNode(tmp.getId(),tmp.getLat(),tmp.getLon()));
            }

                System.out.println(way.getNodes().get(0).getId());
                System.out.println(way.getNodes().get(way.getNodes().size() - 1).getId());
        }

        System.out.println("GRAPH:");
        for(GraphNode n : nodes){
            System.out.println(n.getLat() + "    " +n.getLon());
        }
        */

        //add intersections first
        for(Way way : ways)
        {
            Node tmp = way.getNodes().get(0);

            for(Way cmpWay : ways)
            {
                if(cmpWay.getId() != way.getId()){
                    for(Node cmpNode : cmpWay.getNodes()){
                        if(cmpNode.getId() != tmp.getId() && AlreadyExist(tmp.getId())){ //intersection

                        }
                    }
                }
            }
        }

    }



    private Boolean AlreadyExist(long searchedId)
    {
        for (Node n : GraphNodeList){
            if (n.getId() == searchedId) return true;
        }
        return false;
    }
}

