package com.example.lab6_20201497.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reservas")
public class Reservas {
    @Id
    @Column(name = "idreservas", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReservas;
    @ManyToOne
    @JoinColumn(name = "idusuario", nullable = false)
    private Usuarios idUsuario;
    @ManyToOne
    @JoinColumn(name = "idmesa", nullable = false)
    private Mesas idMesa;
    @Column(name = "fechainicio", nullable = false)
    private LocalDateTime fechaInicio;
    @Column(name = "fechafin", nullable = false)
    private LocalDateTime fechaFin;
}
