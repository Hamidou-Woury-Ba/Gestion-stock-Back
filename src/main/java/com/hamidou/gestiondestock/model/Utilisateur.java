package com.hamidou.gestiondestock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "utilisateur")
public class Utilisateur extends AbstractEntity{

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "datedenaissance")
    private Instant dateDeNaissance;

    @Column(name = "motdepasse")
    private String moteDePasse;

    @Embedded
    private Adresse adresse;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "identreprise")
    private Entreprise entreprise;

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<Roles> roles;

}
