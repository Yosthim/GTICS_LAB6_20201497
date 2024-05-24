package com.example.lab6_20201497.repository;

import com.example.lab6_20201497.entity.Mesas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesasRepository extends JpaRepository<Mesas, Integer> {
}
