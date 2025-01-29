package com.hamidou.gestiondestock.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.hamidou.gestiondestock.dto.MouvementStockDto;

public class MouvementStockValidator {
    public static List<String> validate(MouvementStockDto mouvementStock) {
        List<String> errors = new ArrayList<>();
        if (mouvementStock == null) {
            errors.add("Veuillez renseigner la date du mouvenent");

            return errors;
        }

        if (mouvementStock.getDateMouvementStock() == null) {
            errors.add("Veuillez renseigner la date du mouvenent");
        }

        if (mouvementStock.getQuantite() == null || mouvementStock.getQuantite().compareTo(BigDecimal.ZERO) == 0) {
            errors.add("Veuillez renseigner la quantité du mouvenent");
        }
        
        return errors;
    }
}
