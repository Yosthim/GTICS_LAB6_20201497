package com.example.lab6_20201497.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Rol")
public class Rol {
    @Id
    @Column(name = "idrol", nullable = false)
    private String idRol;
    @Column(nullable = false)
    private String nombre;
}
