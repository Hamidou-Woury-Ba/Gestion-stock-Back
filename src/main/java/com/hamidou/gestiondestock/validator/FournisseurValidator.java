package com.hamidou.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.hamidou.gestiondestock.dto.FournisseurDto;

public class FournisseurValidator {
    public static List<String> validate(FournisseurDto fournisseurDto){
        List<String> errors = new ArrayList<>();

        if(fournisseurDto == null){
            errors.add("Veuillez renseigner le nom du fournisseur");
            errors.add("Veuillez renseigner le prenom du fournisseur");
            errors.add("Veuillez renseigner l'email du fournisseur");
            errors.add("Veuillez renseigner le numéro de téléphone du fournisseur");
            
            return errors;
        }

        if (!StringUtils.hasLength(fournisseurDto.getNom())){
            errors.add("Veuillez renseigner le nom du fournisseur");
        }

        if (!StringUtils.hasLength(fournisseurDto.getPrenom())){
            errors.add("Veuillez renseigner le prenom du fournisseur");
        }

        if (!StringUtils.hasLength(fournisseurDto.getMail())){
            errors.add("Veuillez renseigner l'email du fournisseur");
        }

        if (!StringUtils.hasLength(fournisseurDto.getNumTel())){
            errors.add("Veuillez renseigner le numéro de téléphone du fournisseur");
        }

        return errors;
    }
}
