package com.hamidou.gestiondestock.dto;

import com.hamidou.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {

    private BigDecimal quantite;

    private VentesDto vente;

    private BigDecimal prixUnitaire;

}
