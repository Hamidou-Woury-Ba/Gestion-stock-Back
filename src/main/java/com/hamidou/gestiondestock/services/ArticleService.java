package com.hamidou.gestiondestock.services;

import java.util.List;

import com.hamidou.gestiondestock.dto.ArticleDto;

public interface ArticleService {

    public ArticleDto save(ArticleDto dto);

    public ArticleDto findById(Integer id);

    public ArticleDto findByCodeArticle(String codeArticle);

    List<ArticleDto> findAll();
    
    void delete(Integer id);
    
}
