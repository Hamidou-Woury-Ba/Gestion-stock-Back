package com.hamidou.gestiondestock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamidou.gestiondestock.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer>{

    Optional<Article> findArticleByCodeArticle(String codeArticle);
    
}
