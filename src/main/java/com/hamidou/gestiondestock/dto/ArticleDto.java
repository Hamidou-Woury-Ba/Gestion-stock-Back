package com.hamidou.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

import com.hamidou.gestiondestock.model.Article;

@Builder
@Data
public class ArticleDto {

    private Integer id;

    private String codearticle;

    private String designation;

    private BigDecimal prixUnitaireHT;

    private  BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private CategoryDto category;

    public static ArticleDto fromEntity(Article article){

        if(article == null){
            return null;
        }

        return ArticleDto.builder()
                .id(article.getId())
                .codearticle(article.getCodearticle())
                .designation(article.getDesignation())
                .prixUnitaireHT(article.getPrixUnitaireHT())
                .tauxTva(article.getTauxTva())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .photo(article.getPhoto())
                .build();
    }

    public static Article toEntity(ArticleDto articleDto){

        if(articleDto == null){
            return null;
        }

        Article article = new Article();
        article.setId(articleDto.getId());
        article.setCodearticle(articleDto.getCodearticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaireHT(articleDto.getPrixUnitaireHT());
        article.setTauxTva(articleDto.getTauxTva());
        article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
        article.setPhoto(articleDto.getPhoto());
        return article;
    }

}
