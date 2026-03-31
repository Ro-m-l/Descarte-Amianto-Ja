package com.example.SpringBoot2.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SpringBoot2.models.EmpresaModel;
import com.example.SpringBoot2.repositories.EmpresaRepository;
import com.example.SpringBoot2.services.GeocodeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/rota")
@CrossOrigin(origins = "*")
public class RotaController {

    @Value("${APP_ORS_API_KEY}")
    private String apiKey;

    // Recebe endereços do frontend
    public static class RotaRequest {
        public String startAddress;
        public String endAddress;
    }

    // Classe interna para coordenadas
    public static class Coord {
        public double lat;
        public double lon;

        public Coord(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }
    }

    private final GeocodeService geocodeService;
    private final EmpresaRepository empresaRepository;

    public RotaController(GeocodeService geocodeService, EmpresaRepository empresaRepository) {
        this.geocodeService = geocodeService;
        this.empresaRepository = empresaRepository;
    }

    private Map<String, Object> calcularRota(GeocodeService.Coord start, GeocodeService.Coord end) throws IOException {
        String url = String.format(Locale.US,
                "https://api.openrouteservice.org/v2/directions/driving-car?api_key=%s&start=%f,%f&end=%f,%f",
                apiKey, start.lon, start.lat, end.lon, end.lat);
        System.out.println("URL gerada para ORS: " + url);
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String json = client.execute(new HttpGet(url),
                    httpResponse -> EntityUtils.toString(httpResponse.getEntity()));

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(json);

            JsonNode features = node.path("features");
            if (!features.isArray() || features.size() == 0)
                throw new IOException("Rota não encontrada");

            JsonNode firstFeature = features.get(0);
            JsonNode summary = firstFeature.path("properties").path("summary");
            double distanceKm = summary.path("distance").asDouble(0) / 1000;
            double durationMin = summary.path("duration").asDouble(0) / 60;

            JsonNode coordinatesNode = firstFeature.path("geometry").path("coordinates");
            List<List<Double>> coordinates = new ArrayList<>();
            if (coordinatesNode.isArray()) {
                for (JsonNode coord : coordinatesNode) {
                    if (coord.isArray() && coord.size() >= 2) {
                        coordinates.add(Arrays.asList(coord.get(1).asDouble(), coord.get(0).asDouble())); // [lat, lon]
                    }
                }
            }

            Map<String, Object> result = new HashMap<>();
            result.put("distanceKm", distanceKm);
            result.put("durationMin", durationMin);
            result.put("coordinates", coordinates);

            return result;
        }
    }

    @PostMapping("/rota-empresas")
    public ResponseEntity<Map<String, Object>> getRotaEmpresas(@RequestBody Map<String, Object> request) {
        try {
            String startAddress = (String) request.get("startAddress");
            int numEmpresas = (int) request.get("numEmpresas");
            if (numEmpresas > 50) {
                numEmpresas = 50;
            }
            // Geocode do usuário
            GeocodeService.Coord startCoord = geocodeService.geocode(startAddress);
            System.out.println("Start Coord: " + startCoord.lat + "," + startCoord.lon);
            // Buscar empresas com coordenadas
            List<EmpresaModel> empresas = empresaRepository.findAll();
            List<EmpresaDist> empresasDist = new ArrayList<>();

            for (EmpresaModel empresa : empresas) {
                if (empresa.getCoordenadas() != null && !empresa.getCoordenadas().isEmpty()) {
                    String[] parts = empresa.getCoordenadas().split(",");
                    double lon = Double.parseDouble(parts[0]);
                    double lat = Double.parseDouble(parts[1]);
                    double dist = distance(lat, lon, startCoord.lat, startCoord.lon); // distância Euclidiana/Haversine
                    empresasDist.add(new EmpresaDist(empresa, dist));
                }
            }

            // Ordena por distância
            empresasDist.sort(Comparator.comparingDouble(e -> e.dist));

            // Filtra por diferença de distância
            List<EmpresaDist> empresasParaAPI = new ArrayList<>();
            if (empresasDist.size() > 1) {
                double minDist = empresasDist.get(0).dist;
                double secondDist = empresasDist.get(1).dist;

                if ((secondDist - minDist) > 10) { // diferença grande: só envia a mais próxima
                    empresasParaAPI.add(empresasDist.get(0));
                } else { // diferença pequena: pega as N empresas mais próximas
                    empresasParaAPI = empresasDist.subList(0, Math.min(numEmpresas, empresasDist.size()));
                }
            } else if (!empresasDist.isEmpty()) {
                empresasParaAPI.add(empresasDist.get(0));
            }

            // Para cada empresa selecionada, chama ORS e monta rota
            List<Map<String, Object>> rotas = new ArrayList<>();
            for (EmpresaDist ed : empresasParaAPI) {
                GeocodeService.Coord endCoord = new GeocodeService.Coord(
                        Double.parseDouble(ed.empresa.getCoordenadas().split(",")[1]),
                        Double.parseDouble(ed.empresa.getCoordenadas().split(",")[0]));

                // PRINT para debug: coordenadas usadas para o cálculo da rota
                System.out.println("Tentando rota para empresa: " + ed.empresa.getNome() +
                        " | End Coord: " + endCoord.lat + "," + endCoord.lon +
                        " | Distância calculada: " + ed.dist + " km");

                try {
                    Map<String, Object> rota = calcularRota(startCoord, endCoord); // método que já faz fetch para ORS
                    rota.put("empresa", ed.empresa.getNome());
                    rotas.add(rota);
                } catch (IOException e) {
                    System.out.println("Rota não encontrada para empresa: " + ed.empresa.getNome());
                }
            }

            Map<String, Object> resposta = new LinkedHashMap<>();
            resposta.put("startCoord", Map.of(
                    "lat", startCoord.lat,
                    "lon", startCoord.lon));
            resposta.put("rotas", rotas);

            return ResponseEntity.ok(resposta);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    // Classe auxiliar
    private static class EmpresaDist {
        EmpresaModel empresa;
        double dist;

        EmpresaDist(EmpresaModel empresa, double dist) {
            this.empresa = empresa;
            this.dist = dist;
        }
    }

    // Fórmula Haversine
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // retorna distância em km
    }

}
