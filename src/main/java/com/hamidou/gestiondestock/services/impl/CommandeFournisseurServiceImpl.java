package com.hamidou.gestiondestock.services.impl;

import com.hamidou.gestiondestock.dto.CommandeFournisseurDto;
import com.hamidou.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.hamidou.gestiondestock.exception.EntityNotFoundException;
import com.hamidou.gestiondestock.exception.ErrorCodes;
import com.hamidou.gestiondestock.exception.InvalidEntityException;
import com.hamidou.gestiondestock.model.*;
import com.hamidou.gestiondestock.repository.ArticleRepository;
import com.hamidou.gestiondestock.repository.CommandeFournisseurRepository;
import com.hamidou.gestiondestock.repository.FournisseurRepository;
import com.hamidou.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.hamidou.gestiondestock.services.CommandeFournisseurService;
import com.hamidou.gestiondestock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
            FournisseurRepository fournisseurRepository, ArticleRepository articleRepository,
            LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {

        List<String> errors = CommandeFournisseurValidator.validate(commandeFournisseurDto);

        if (!errors.isEmpty()) {
            log.error("Commande fournisseur is not valid {}", commandeFournisseurDto);
            throw new InvalidEntityException("La commande fournisseur n'est pas valide",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository
                .findById(commandeFournisseurDto.getFournisseur().getId());

        if (!fournisseur.isEmpty()) {
            log.warn("Fournisseur with ID {} was not found in the DB", commandeFournisseurDto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun fournisseur avec l'ID "
                    + commandeFournisseurDto.getFournisseur().getId() + " n'a été trouvé dans la BDD",
                    ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (commandeFournisseurDto.getLigneCommandeFournisseur() != null) {
            commandeFournisseurDto.getLigneCommandeFournisseur().forEach(ligCmdFourn -> {
                if (ligCmdFourn.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(ligCmdFourn.getId());
                    if (article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID " + ligCmdFourn.getArticle().getId() + " n'existe pas");
                    }
                } else {
                    errors.add("Impossible d'enregistrer une commande avec un article NULL");
                }
            });
        }

        if (articleErrors.isEmpty()) {
            log.error("Un ou plusieurs articles de la commande n'ont pas été trouvés dans la BDD");
            throw new InvalidEntityException(
                    "Un ou plusieurs articles de la commande n'ont pas été trouvés dans la BDD",
                    ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeFournisseur savedCommandeFournisseur = commandeFournisseurRepository
                .save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));

        if (commandeFournisseurDto.getLigneCommandeFournisseur() != null) {
            commandeFournisseurDto.getLigneCommandeFournisseur().forEach(lignCmdFour -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(lignCmdFour);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCommandeFournisseur);
                ligneCommandeFournisseurRepository.save(LigneCommandeFournisseurDto.toEntity(lignCmdFour));
            });
        }

        return CommandeFournisseurDto.fromEntity(savedCommandeFournisseur);
    }

    @Override
    public CommandeFournisseurDto updateEtatCommande(Integer idCommande) {
        return null;
    }

    @Override
    public CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande,
            BigDecimal quantite) {
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

        if (id == null) {
            log.error("Commande fournisseur ID is NULL");
            return null;
        }

        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande fournisseur avec l'ID " + id + " n'a été trouvée dans la BDD",
                        ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {

        if (StringUtils.hasLength(code)){
            log.error("Commande fournisseur code is NULL");
            return null;
        }

        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune commande client avec le code " + code + " n'a été trouvée dans la BDD", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(
            Integer idCommande) {
        return null;
    }

    @Override
    public void delete(Integer id) {

        if (id == null){
            log.error("Commande fournisseur ID is NULL");
            return;
        }

        commandeFournisseurRepository.deleteById(id);

    }
}
