package com.example.lab6_20201497.repository;

import com.example.lab6_20201497.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    public Usuarios findByEmail(String email);
}
