package com.hamidou.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamidou.gestiondestock.model.Ventes;

public interface VentesRepository extends JpaRepository<Integer, Ventes>{
    
}
