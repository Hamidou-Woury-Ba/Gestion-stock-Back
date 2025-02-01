package com.hamidou.gestiondestock.services;

import java.util.List;

import com.hamidou.gestiondestock.dto.EntrepriseDto;

public interface EntrepriseService {
    
    EntrepriseDto save(EntrepriseDto entrepriseDto);
    
    EntrepriseDto findById(Integer id);
    
    List<EntrepriseDto> findAll();
    
    void delete(Integer id);
    

}
