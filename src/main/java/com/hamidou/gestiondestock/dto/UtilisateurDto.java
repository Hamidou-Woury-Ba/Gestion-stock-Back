package com.hamidou.gestiondestock.dto;

import com.hamidou.gestiondestock.model.Adresse;
import com.hamidou.gestiondestock.model.Entreprise;
import com.hamidou.gestiondestock.model.Roles;

import java.time.Instant;
import java.util.List;

public class UtilisateurDto {

    private String nom;

    private String prenom;

    private String email;

    private Instant dateDeNaissance;

    private String moteDePasse;

    private AdresseDto adresse;

    private String photo;

    private Entreprise entreprise;

    private List<RolesDto> roles;

}
