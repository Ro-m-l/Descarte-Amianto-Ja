package com.example.SpringBoot2.repositories;

import com.example.SpringBoot2.models.EmpresaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaModel, Integer> {
    // Nenhum método extra é obrigatório para o básico
}