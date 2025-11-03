package com.example.SpringBoot2;

import com.example.SpringBoot2.controllers.EmpresaController;
import com.example.SpringBoot2.models.EmpresaModel;
import com.example.SpringBoot2.repositories.EmpresaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class EmpresaIntegrationTest {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaController empresaController;

    @Test
    void testGeocodeEmpresasRealAPI() {
        // Pré-condição: insere empresa sem coordenadas
        EmpresaModel e = new EmpresaModel("Padaria Central", "Rua Augusta, São Paulo - SP");
        empresaRepository.save(e);

        // Ação: roda o geocodeAll real
        String result = empresaController.geocodeEmpresas();

        // Verifica se atualizou coordenadas
        EmpresaModel atualizado = empresaRepository.findAll().get(0);
        assertNotNull(atualizado.getCoordenadas(), "As coordenadas não foram atualizadas!");
        System.out.println(result);

        // Inverte de lon,lat (API) para lat,lon (mais intuitivo)
        String coords = atualizado.getCoordenadas();
        if (coords != null && !coords.isEmpty()) {
            String[] parts = coords.split(",");
            if (parts.length == 2) {
                String latLon = parts[1] + "," + parts[0];
                System.out.println("Coord obtida: " + latLon);
            }
        }
    }
}