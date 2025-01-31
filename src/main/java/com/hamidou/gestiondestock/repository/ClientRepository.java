package com.hamidou.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamidou.gestiondestock.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{
    
}
