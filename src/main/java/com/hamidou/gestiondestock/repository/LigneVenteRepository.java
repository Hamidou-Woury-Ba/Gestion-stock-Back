package com.hamidou.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamidou.gestiondestock.model.LigneVente;

public interface LigneVenteRepository extends JpaRepository<Integer, LigneVente>{
    
}
