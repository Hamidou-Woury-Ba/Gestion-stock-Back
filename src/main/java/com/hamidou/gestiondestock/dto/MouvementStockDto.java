package com.hamidou.gestiondestock.dto;

import com.hamidou.gestiondestock.model.Article;
import com.hamidou.gestiondestock.model.TypeMouvementStock;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MouvementStockDto {

    private Instant dateMouvementStock;

    private BigDecimal quantite;

    private Article article;

    private TypeMouvementStock typeMouvementStock;

}
