package com.example.SpringBoot2.controllers;

import com.example.SpringBoot2.models.EmpresaModel;
import com.example.SpringBoot2.repositories.EmpresaRepository;
import com.example.SpringBoot2.services.GeocodeService;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaRepository empresaRepository;
    private final GeocodeService geocodeService;

    public EmpresaController(EmpresaRepository empresaRepository, GeocodeService geocodeService) {
        this.empresaRepository = empresaRepository;
        this.geocodeService = geocodeService;
    }

    @GetMapping
    public List<EmpresaModel> listarTodas() {
        // Retorna JSON automaticamente
        return empresaRepository.findAll();
    }

    @PostMapping("/geocode-all")
    public String geocodeEmpresas() {
        List<EmpresaModel> empresas = empresaRepository.findAll();
        int updatedCount = 0;

        for (EmpresaModel empresa : empresas) {
            if (empresa.getCoordenadas() == null || empresa.getCoordenadas().isEmpty()) {
                try {
                    GeocodeService.Coord coord = geocodeService.geocode(empresa.getEndereco());
                    if (coord != null) {
                        empresa.setCoordenadas(coord.lon + "," + coord.lat);
                        empresaRepository.save(empresa);
                        updatedCount++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return updatedCount + " empresas atualizadas com coordenadas.";
    }

}
