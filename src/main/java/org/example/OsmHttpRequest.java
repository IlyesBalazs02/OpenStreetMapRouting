package org.example;

public class OsmHttpRequest {
    public OsmHttpRequest(double minLat, double minLon, double maxLat, double maxLon)
    {
        String requestUrl = "https://overpass-api.de/api/interpreter?data=[out:json];way[%27highway%27](" + minLat + ",%20" + minLon + ",%20" + maxLat + ",%20"+ maxLon +");out%20body;%3E;out%20skel%20qt;";
    }
}
