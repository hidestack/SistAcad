package com.multiversa.SistAcad.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "aluno-model")
@Data
public class AlunoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeAluno", nullable = false)
    private String nomeAluno;

}
