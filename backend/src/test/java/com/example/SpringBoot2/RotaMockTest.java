package com.example.SpringBoot2;

import com.example.SpringBoot2.models.EmpresaModel;
import com.example.SpringBoot2.repositories.EmpresaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class RotaMockTest {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Test
    void testEmpresaCoordinatesIntegration() {
        // Limpa o banco
        empresaRepository.deleteAll();

        // Cria empresas mock com coordenadas
        EmpresaModel e1 = new EmpresaModel("Padaria Central", "Rua Augusta, SP");
        e1.setCoordenadas("-23.550078,-46.646495");
        EmpresaModel e2 = new EmpresaModel("Padaria do Bairro", "Av. Paulista, SP");
        e2.setCoordenadas("-23.561,-46.653");

        empresaRepository.saveAll(List.of(e1, e2));

        // Verifica se foram salvas corretamente
        var empresas = empresaRepository.findAll();
        assertEquals(2, empresas.size(), "Deveria ter 2 empresas");

        empresas.forEach(emp -> System.out.println(emp.getNome() + " | " + emp.getCoordenadas()));
    }
}