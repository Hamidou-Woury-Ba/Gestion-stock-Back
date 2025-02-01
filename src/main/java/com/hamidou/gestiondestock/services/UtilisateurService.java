package com.hamidou.gestiondestock.services;

import java.util.List;

import com.hamidou.gestiondestock.dto.UtilisateurDto;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto utilisateurDto);

    UtilisateurDto findById(Integer id);

    List<UtilisateurDto> findAll();

    void delete(Integer id);
    
}
