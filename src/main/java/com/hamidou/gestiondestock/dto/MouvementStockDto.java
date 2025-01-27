package com.hamidou.gestiondestock.dto;

import com.hamidou.gestiondestock.model.Article;
import com.hamidou.gestiondestock.model.MouvementStock;
import com.hamidou.gestiondestock.model.TypeMouvementStock;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MouvementStockDto {

    private Integer id;

    private Instant dateMouvementStock;

    private BigDecimal quantite;

    private Article article;

    private TypeMouvementStock typeMouvementStock;
    
    private Integer idEntreprise;

    public static MouvementStockDto fromEntity(MouvementStock mouvementStock){
        if(mouvementStock == null){
            return null;
        }

        return MouvementStockDto.builder()
                .id(mouvementStock.getId())
                .dateMouvementStock(mouvementStock.getDateMouvementStock())
                .quantite(mouvementStock.getQuantite())
                .idEntreprise(mouvementStock.getIdEntreprise())
                .build();
    }

    public static MouvementStock toEntity(MouvementStockDto mouvementStockDto){
        if(mouvementStockDto == null){
            return null;
        }

        MouvementStock mouvementStock = new MouvementStock();
        mouvementStock.setId(mouvementStockDto.getId());
        mouvementStock.setDateMouvementStock(mouvementStockDto.getDateMouvementStock());
        mouvementStock.setQuantite(mouvementStockDto.getQuantite());
        mouvementStock.setIdEntreprise(mouvementStockDto.getIdEntreprise());

        return mouvementStock;
    }

}
