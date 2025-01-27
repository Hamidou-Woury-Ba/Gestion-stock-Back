package com.hamidou.gestiondestock.dto;

import com.hamidou.gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolesDto {

    private String roleName;

    private UtilisateurDto utilisateur;

}
