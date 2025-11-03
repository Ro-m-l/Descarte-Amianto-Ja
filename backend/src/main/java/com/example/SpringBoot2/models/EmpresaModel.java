package com.example.SpringBoot2.models;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_EMPRESAS")
public class EmpresaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;

    @Column
    private String nome;

    @Column
    private String endereco;

    @Column
    private String telefone;

    @Column
    private String email;

    @Column
    private String site;

    @Column
    private String coordenadas;

    // Construtor sem parâmetros necessário para JPA
    public EmpresaModel() {
    }

    // Construtor de conveniência só para testes
    public EmpresaModel(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    /*
    // Quando formos salvar imagens diretamente no banco
    // @Lob indica que é um campo grande (BLOB)
    @Lob
    @Column
    private byte[] imagem;
    */

    // Getters apenas (não precisa de setters, já que a tabela não será alterada via site)
    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getSite() {
        return site;
    }

    public String getCoordenadas() {
        return coordenadas;
    }
    
    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

  /*public byte[] getImagem() {
        return imagem;
    } */  
}
