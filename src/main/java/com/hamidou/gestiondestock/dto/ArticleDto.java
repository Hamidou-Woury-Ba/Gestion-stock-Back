package com.hamidou.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ArticleDto {

    private String codearticle;

    private String designation;

    private BigDecimal prixUnitaireHT;

    private  BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private CategoryDto category;

}
