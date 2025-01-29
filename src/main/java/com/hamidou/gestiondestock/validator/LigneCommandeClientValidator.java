package com.hamidou.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import com.hamidou.gestiondestock.dto.LigneCommandeClientDto;

public class LigneCommandeClientValidator {
     public static List<String> validate(LigneCommandeClientDto ligneCommandeClientDto) {
    List<String> errors = new ArrayList<>();

    if (ligneCommandeClientDto == null) {
      errors.add("Veuillez renseigner le quantite de la ligne de commande");
      errors.add("Veuillez renseigner le prix unitaire de la ligne de commande");
      
      return errors;
    }

    if (ligneCommandeClientDto.getQuantite() == null) {
      errors.add("Veuillez renseigner le quantite de la ligne de commande");
    }

    if (ligneCommandeClientDto.getPrixUnitaire() == null) {
      errors.add("Veuillez renseigner le prix unitaire de la ligne de commande");
    }

    return errors;
  }
}
