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

    private GraphNode SearchById(long id)
    {
        for (GraphNode node : nodes)
        {
            if(node.getId() == id)return node;
        }
        return null;
    }

    public static final double RADIUS_OF_EARTH = 6371; // Earth's radius in kilometers

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convert latitude and longitude from degrees to radians
        double lat1Radians = Math.toRadians(lat1);
        double lon1Radians = Math.toRadians(lon1);
        double lat2Radians = Math.toRadians(lat2);
        double lon2Radians = Math.toRadians(lon2);

        // Calculate differences between latitudes and longitudes
        double latDiff = lat2Radians - lat1Radians;
        double lonDiff = lon2Radians - lon1Radians;

        // Haversine formula
        double a = Math.pow(Math.sin(latDiff / 2), 2)
                + Math.cos(lat1Radians) * Math.cos(lat2Radians)
                * Math.pow(Math.sin(lonDiff / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate distance
        double distanceInMeters = RADIUS_OF_EARTH * c * 1000; // Convert km to meters
        return distanceInMeters;
    }

}

