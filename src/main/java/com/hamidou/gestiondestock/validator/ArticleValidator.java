package com.hamidou.gestiondestock.validator;

import com.hamidou.gestiondestock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate(ArticleDto articleDto){
        List<String> errors = new ArrayList<>();

        if (articleDto == null){
            errors.add("Veuillez renseigner le code de l'article");
            errors.add("Veuillez renseigner la designation de l'article");
            errors.add("Veuillez renseigner le prix unitaire de l'article");
            errors.add("Veuillez renseigner le taux de TVA de l'article");
            errors.add("Veuillez renseigner le prix unitaire TTC de l'article");
            errors.add("Veuillez renseigner la categorie de l'article"); 
            
            return errors;
        }

        if (!StringUtils.hasLength(articleDto.getCodearticle())){
            errors.add("Veuillez renseigner le code de l'article");
        }

        if (!StringUtils.hasLength(articleDto.getDesignation())){
            errors.add("Veuillez renseigner la designation de l'article");
        }

        if (articleDto.getPrixUnitaireHT() == null){
            errors.add("Veuillez renseigner le prix unitaire de l'article");
        }

        if (articleDto.getTauxTva() == null){
            errors.add("Veuillez renseigner le taux de TVA de l'article");
        }

        if(articleDto.getPrixUnitaireTtc() == null){
            errors.add("Veuillez renseigner le prix unitaire TTC de l'article");
        }

        if (articleDto.getCategory() == null){
            errors.add("Veuillez renseigner la categorie de l'article");
        }

        return errors;
    }

}
