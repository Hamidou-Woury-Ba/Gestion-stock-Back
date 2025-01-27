package com.hamidou.gestiondestock.dto;

import com.hamidou.gestiondestock.model.Adresse;
import com.hamidou.gestiondestock.model.CommandeCLient;
import jakarta.persistence.Embedded;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class ClientDto {

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String photo;

    private String mail;

    private String numTel;

    private List<CommandeClientDto> commandeClients;

}
