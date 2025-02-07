package com.hamidou.gestiondestock.repository;

import com.hamidou.gestiondestock.dto.CommandeFournisseurDto;
import com.hamidou.gestiondestock.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {

    Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);

}
