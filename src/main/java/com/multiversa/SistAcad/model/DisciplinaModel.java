package com.multiversa.SistAcad.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "disciplina-model")
@Data
public class

DisciplinaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeDisciplina", nullable = false)
    private String nomeDisciplina;

}
