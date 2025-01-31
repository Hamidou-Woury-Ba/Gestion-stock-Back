package com.hamidou.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamidou.gestiondestock.model.LigneCommandeClient;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer>{
    
}
