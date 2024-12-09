package com.multiversa.SistAcad.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Matricula-model")
@Data
public class

MatriculaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numeroMatricula", nullable = false)
    private String numeroMatricula;

}
