package com.hamidou.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamidou.gestiondestock.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Integer, Utilisateur>{
    
}
