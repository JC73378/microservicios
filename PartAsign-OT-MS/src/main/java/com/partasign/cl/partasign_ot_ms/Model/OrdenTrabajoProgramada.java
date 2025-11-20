package com.partasign.cl.partasign_ot_ms.Model;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ordenes_trabajo_programadas")
public class OrdenTrabajoProgramada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombreTecnico;

    @Column(nullable = false)
    private String apellidoTecnico;

    @Column(nullable = false)
    private String rutTecnico;

    @Column(nullable = false)
    private String numeroEquipo;

    @Column(nullable = false)
    private LocalDate fechaFirma;

    @Column(nullable = false)
    private String repuestoId;
}
