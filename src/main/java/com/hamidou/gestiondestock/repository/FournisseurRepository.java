package com.hamidou.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamidou.gestiondestock.model.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer>{
    
}
