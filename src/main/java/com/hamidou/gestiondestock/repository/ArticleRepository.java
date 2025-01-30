package com.hamidou.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamidou.gestiondestock.model.Article;

public interface ArticleRepository extends JpaRepository<Integer, Article> {

    
}
