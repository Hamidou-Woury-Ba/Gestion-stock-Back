package com.hamidou.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamidou.gestiondestock.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

    Optional<Category> findCategoryByCodeCategory(String codeCategory);

}
