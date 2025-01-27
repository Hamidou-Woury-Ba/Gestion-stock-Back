package com.hamidou.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
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
    private  TypeMouvementStock typeMouvementStock;

}
