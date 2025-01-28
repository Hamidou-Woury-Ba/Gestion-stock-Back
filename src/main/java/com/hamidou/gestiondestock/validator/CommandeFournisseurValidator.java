package com.hamidou.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.hamidou.gestiondestock.dto.CommandeFournisseurDto;

public class CommandeFournisseurValidator {
    public static List<String> validate(CommandeFournisseurDto commandeFournisseurDto) {
        List<String> errors = new ArrayList<>();
        if (commandeFournisseurDto == null) {
            errors.add("Veuillez renseigner le code de la commande");
            errors.add("Veuillez renseigner la date de la commande");

            return errors;
        }

        if (!StringUtils.hasLength(commandeFournisseurDto.getCode())) {
            errors.add("Veuillez renseigner le code de la commande");
        }
        if (commandeFournisseurDto.getDateCommande() == null) {
            errors.add("Veuillez renseigner la date de la commande");
        }

        return errors;
    }
}
