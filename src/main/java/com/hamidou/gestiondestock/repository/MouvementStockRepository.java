package com.hamidou.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamidou.gestiondestock.model.MouvementStock;

public interface MouvementStockRepository extends JpaRepository<Integer, MouvementStock>{
    
}
