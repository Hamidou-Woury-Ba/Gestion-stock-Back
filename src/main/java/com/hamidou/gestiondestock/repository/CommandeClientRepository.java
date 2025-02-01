package com.hamidou.gestiondestock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamidou.gestiondestock.model.CommandeClient;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer>{
    
    Optional<CommandeClient> findCommandeClientByCode(String code);
    
} 
