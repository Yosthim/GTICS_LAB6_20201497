package com.example.lab6_20201497.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Mesas")
public class Mesas {
    @Id
    @Column(name = "idmesas", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMesas;
    @Column(name = "tipo", nullable = false)
    private String nombre;
    @Column(nullable = false)
    private int capacidad;
    @Column(nullable = false)
    private String ubicacion;
    @Column(nullable = false)
    private int disponibilidad;
    @Column(nullable = false)
    private String estado;
}
