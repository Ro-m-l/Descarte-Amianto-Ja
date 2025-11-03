package com.example.SpringBoot2.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import java.io.IOException;
import java.net.URLEncoder;

@Service
public class GeocodeService {

    @Value("${ORS_API_KEY}")
    private String apiKey;

    // Classe para coordenadas
    public static class Coord {
        public double lat;
        public double lon;
        public Coord(double lat, double lon) { this.lat = lat; this.lon = lon; }
    }

    // Método de geocoding
    public Coord geocode(String address) throws IOException {
        String url = "https://api.openrouteservice.org/geocode/search?api_key=" + apiKey +
                "&text=" + URLEncoder.encode(address, "UTF-8") + "&size=1";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String json = client.execute(new HttpGet(url), httpResponse ->
                    EntityUtils.toString(httpResponse.getEntity()));

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(json);
            JsonNode features = node.path("features");

            if (features.isArray() && features.size() > 0) {
                JsonNode first = features.get(0).path("geometry").path("coordinates");
                return new Coord(first.get(1).asDouble(), first.get(0).asDouble());
            }
        }
        return null;
    }
}