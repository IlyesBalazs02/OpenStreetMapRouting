package org.example;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OsmHttpRequest {
    public OsmHttpRequest(double minLat, double minLon, double maxLat, double maxLon)
    {
        String requestUrl = "https://overpass-api.de/api/interpreter?data=[out:json];way[%27highway%27](" + minLat + ",%20" + minLon + ",%20" + maxLat + ",%20"+ maxLon +");out%20body;%3E;out%20skel%20qt;";
        Process(requestUrl);
    }

    public void Process(String requestUrl)
    {
        String jsonResponse;
        try {
            jsonResponse = sendHttpRequest(requestUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Gson gson = new Gson();

        List<Node> nodeList = new ArrayList<>();
        List<Way> wayList = new ArrayList<>();

        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
        JsonArray elements = jsonObject.getAsJsonArray("elements");

        // Process nodes
        for (JsonElement element : elements) {
            JsonObject obj = element.getAsJsonObject();
            String type = obj.get("type").getAsString();

            if (type.equals("node")) {
                long id = obj.get("id").getAsLong();
                double lat = obj.get("lat").getAsDouble();
                double lon = obj.get("lon").getAsDouble();

                nodeList.add(new Node(id, lat, lon));
            }
        }

        // Process ways
        for (JsonElement element : elements) {
            JsonObject obj = element.getAsJsonObject();
            String type = obj.get("type").getAsString();

            if (type.equals("way")) {
                long id = obj.get("id").getAsLong();
                Way way = new Way(id);

                JsonArray nodesArray = obj.getAsJsonArray("nodes");
                for (JsonElement nodeElement : nodesArray) {
                    long nodeId = nodeElement.getAsLong();
                    for (Node node : nodeList){
                        if(node.getId() == nodeId){ // need a reference for the coordinates
                            way.addNode(node);
                        }
                    }
                }

                wayList.add(way);
            }
        }

    }

    //Send the request and return the osm nodes and ways in json format
    private static String sendHttpRequest(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            return response.toString();
        } finally {
            urlConnection.disconnect();
        }
    }
}
