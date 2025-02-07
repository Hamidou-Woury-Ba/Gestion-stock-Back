package com.hamidou.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "mouvementstock")
public class MouvementStock extends AbstractEntity{

    @Column(name = "datemouvementstock")
    private  Instant dateMouvementStock;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @Column(name = "typemouvementstock")
    @Enumerated(EnumType.STRING)
    private TypeMouvementStock typeMouvementStock;
  
    @Column(name = "sourcemouvementstock")
    @Enumerated(EnumType.STRING)
    private SourceMouvementStock sourceMouvementStock;

    @Column(name = "identreprise")
    private Integer idEntreprise;

}
