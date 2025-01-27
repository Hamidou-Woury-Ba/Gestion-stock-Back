package com.hamidou.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VentesDto {

    private String code;

    private Instant dateVente;

    private String commentaire;

    private Integer idEntreprise;

    private List<LigneVenteDto> ligneVentes;

}
