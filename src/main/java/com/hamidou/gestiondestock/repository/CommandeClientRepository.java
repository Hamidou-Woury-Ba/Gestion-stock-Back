package com.hamidou.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamidou.gestiondestock.model.CommandeClient;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer>{
    
    
} 
