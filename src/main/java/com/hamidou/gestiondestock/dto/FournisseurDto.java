package com.hamidou.gestiondestock.dto;

import com.hamidou.gestiondestock.model.Adresse;
import com.hamidou.gestiondestock.model.CommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class FournisseurDto {

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String photo;

    private String mail;

    private String numTel;

    private List<CommandeFournisseurDto> commandeFournisseur;

}
