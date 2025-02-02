package com.hamidou.gestiondestock.services.impl;

import com.hamidou.gestiondestock.dto.CommandeFournisseurDto;
import com.hamidou.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.hamidou.gestiondestock.services.CommandeFournisseurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {
    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        return null;
    }

    @Override
    public CommandeFournisseurDto updateEtatCommande(Integer idCommande) {
        return null;
    }

    @Override
    public CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return null;
    }

    @Override
    public CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur) {
        return null;
    }

    @Override
    public CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        return null;
    }

    @Override
    public CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        return null;
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        return null;
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        return null;
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return null;
    }

    @Override
    public List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
