package com.hamidou.gestiondestock.services;

import java.util.List;

import com.hamidou.gestiondestock.dto.FournisseurDto;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto fournisseurDto);

    FournisseurDto findById(Integer id);

    List<FournisseurDto> findAll();

    void delete(Integer id);
    
}
