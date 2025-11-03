package com.example.SpringBoot2;

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

    @Test
    void testUpdateEmpresaCoordinates() {
        // Limpa banco
        empresaRepository.deleteAll();

        // Cria empresa sem coordenadas
        EmpresaModel empresa = new EmpresaModel("Padaria Central", "Rua Augusta, SP");
        empresaRepository.save(empresa);

        // Atualiza coordenadas diretamente
        empresa.setCoordenadas("-23.550078,-46.646495");
        empresaRepository.save(empresa);

        // Busca e valida
        EmpresaModel atualizado = empresaRepository.findAll().get(0);
        assertNotNull(atualizado.getCoordenadas(), "Coordenadas deveriam estar atualizadas");
        System.out.println(atualizado.getNome() + " | " + atualizado.getCoordenadas());
    }
}