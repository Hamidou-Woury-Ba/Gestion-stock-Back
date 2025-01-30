package com.hamidou.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamidou.gestiondestock.model.Category;

public interface CategoryRepository extends JpaRepository<Integer, Category>{
    
}
