package com.example.lab6_20201497.repository;

import com.example.lab6_20201497.entity.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservasRepository extends JpaRepository<Reservas, Integer> {
}
