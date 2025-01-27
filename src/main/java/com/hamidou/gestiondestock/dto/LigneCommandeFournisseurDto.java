package com.hamidou.gestiondestock.dto;

import com.hamidou.gestiondestock.model.Article;
import com.hamidou.gestiondestock.model.CommandeCLient;
import com.hamidou.gestiondestock.model.CommandeFournisseur;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeFournisseurDto {

    private ArticleDto article;

    private CommandeFournisseurDto commandeFournisseur;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

}
