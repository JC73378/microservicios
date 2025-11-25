package com.partasign.recepciones.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "recepciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recepcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String repuestoId;

    @Column(nullable = false)
    private Integer cantidadRecibida;

    @Column(nullable = false)
    private String recibidoPor;

    @Column(nullable = false)
    private LocalDate fechaRecepcion;

    @Column(nullable = true, length = 1000)
    private String observacion;
}

