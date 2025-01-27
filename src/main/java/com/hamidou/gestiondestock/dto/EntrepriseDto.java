package com.hamidou.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EntrepriseDto {

    private String nom;

    private String description;

    private AdresseDto adresse;

    private String codeFiscal;

    private String photo;

    private String email;

    private String numTel;

    private String siteWeb;

    private List<UtilisateurDto> utiisateurs;

}
