package com.example.SpringBoot2;

import com.example.SpringBoot2.controllers.RotaController;
import com.example.SpringBoot2.models.EmpresaModel;
import com.example.SpringBoot2.repositories.EmpresaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class RotaIntegrationTest {

    @Autowired
    private RotaController rotaController;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Test
    void testRotaEmpresasRealAPI() throws Exception {
        // Limpa o banco de teste
        empresaRepository.deleteAll();

        // Cria empresas com coordenadas conhecidas (lat,lon)
        EmpresaModel e1 = new EmpresaModel("Padaria Central", "Rua Augusta, São Paulo - SP");
        e1.setCoordenadas("-46.646495,-23.550078"); // lon,lat conforme API
        empresaRepository.save(e1);

        EmpresaModel e2 = new EmpresaModel("Padaria do Bairro", "Av. Paulista, São Paulo - SP");
        e2.setCoordenadas("-46.653,-23.561"); // lon,lat
        empresaRepository.save(e2);

        // Monta request para rota
        Map<String,Object> request = new HashMap<>();
        request.put("startAddress", "Praça da Sé, São Paulo - SP");
        request.put("numEmpresas", 2);

        // Chama o endpoint
        ResponseEntity<Map<String,Object>> response = rotaController.getRotaEmpresas(request);

        Map<String,Object> body = response.getBody();
        assertNotNull(body);

        if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            System.out.println("Erro na API: " + body.get("error"));
            fail("Falha na API: " + body.get("error"));
        } else {
            System.out.println("Start Coord: " + body.get("startCoord"));
            System.out.println("Rotas retornadas: ");

            @SuppressWarnings("unchecked")
            Iterable<Map<String,Object>> rotas = (Iterable<Map<String,Object>>) body.get("rotas");

            int count = 0;
            for (Map<String,Object> rota : rotas) {
                assertTrue(rota.containsKey("distanceKm"));
                assertTrue(rota.containsKey("durationMin"));
                assertTrue(rota.containsKey("coordinates"));
                assertTrue(rota.containsKey("empresa"));

                System.out.println("Empresa: " + rota.get("empresa") +
                        " | Distância: " + rota.get("distanceKm") + " km" +
                        " | Duração: " + rota.get("durationMin") + " min");
                count++;
            }

            assertTrue(count > 0, "Nenhuma rota foi retornada!");
        }
    }
}