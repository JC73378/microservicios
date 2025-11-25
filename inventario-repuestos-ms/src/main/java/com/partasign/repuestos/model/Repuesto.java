package com.partasign.repuestos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "repuestos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String codigo365;

    @Column(nullable = false, unique = true)
    private String codigoBarras;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer stockActual;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private String categoria;
}

