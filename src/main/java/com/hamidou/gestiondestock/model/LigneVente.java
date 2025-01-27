package com.hamidou.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lignevente")
public class LigneVente extends AbstractEntity{

    private BigDecimal quantite;

    @ManyToOne
    @JoinColumn(name = "idvente")
    private Ventes vente;

    @Column(name = "prixunitaire")
    private BigDecimal prixUnitaire;

}
