package com.example.lab6_20201497.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuarios {
    @Id
    @Column(name = "idusuarios", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean estado;
    @ManyToOne
    @JoinColumn(name = "idrol", nullable = false)
    private Rol idRol;
}
