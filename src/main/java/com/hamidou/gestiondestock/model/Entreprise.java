package com.hamidou.gestiondestock.model;

import com.hamidou.gestiondestock.dto.UtilisateurDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "entreprise")
public class Entreprise extends AbstractEntity{

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Embedded
    private Adresse adresse;

    @Column(name = "codefiscal")
    private String codeFiscal;

    @Column(name = "photo")
    private String photo;

    @Column(name = "email")
    private String email;

    @Column(name = "numTel")
    private String numTel;

    @Column(name = "siteweb")
    private String siteWeb;

    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utiisateurs;

}
