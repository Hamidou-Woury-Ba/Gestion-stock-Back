package com.hamidou.gestiondestock.services.impl;

import com.hamidou.gestiondestock.dto.LigneVenteDto;
import com.hamidou.gestiondestock.dto.VentesDto;
import com.hamidou.gestiondestock.exception.ErrorCodes;
import com.hamidou.gestiondestock.exception.InvalidEntityException;
import com.hamidou.gestiondestock.model.Article;
import com.hamidou.gestiondestock.model.LigneVente;
import com.hamidou.gestiondestock.model.Ventes;
import com.hamidou.gestiondestock.repository.ArticleRepository;
import com.hamidou.gestiondestock.repository.LigneVenteRepository;
import com.hamidou.gestiondestock.repository.VentesRepository;
import com.hamidou.gestiondestock.services.VentesService;
import com.hamidou.gestiondestock.validator.VentesValidator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

    private VentesRepository ventesRepository;
    private ArticleRepository articleRepository;
    private LigneVenteRepository ligneVenteRepository;

    public VentesServiceImpl(VentesRepository ventesRepository, ArticleRepository articleRepository, LigneVenteRepository ligneVenteRepository) {
        this.ventesRepository = ventesRepository;
        this.articleRepository = articleRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VentesDto save(VentesDto ventesDto) {

        List<String> errors = VentesValidator.validate(ventesDto);

        if (errors.isEmpty()){
            log.error("Ventes is valid");
            throw new InvalidEntityException("L'entité vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID);
        } 

        List<String> articleErrors = new ArrayList<>();

        ventesDto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if (article.isEmpty()){
                articleErrors.add("Aucun article avec l'ID " + ligneVenteDto.getArticle().getId() + " na été trouvé dans la BDD");
            }
        });
        
        if (articleErrors.isEmpty()) {
            log.error("Un ou plusieurs articles de la commande n'ont pas été trouvés dans la BDD {}", errors);
            throw new InvalidEntityException(
                    "Un ou plusieurs articles de la commande n'ont pas été trouvés dans la BDD",
                    ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(ventesDto));

        ventesDto.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVentes);
            ligneVenteRepository.save(ligneVente);
        });

        return VentesDto.fromEntity(savedVentes);
    }

    @Override
    public VentesDto findById(Integer id) {
       
        if (id == null){
            log.error("Ventes ID is null");
            return null;
        }

        return ventesRepository.findById(id)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new InvalidEntityException("Aucune vente avec l'ID " + id + " n'a été trouvée dans la BDD",
                        ErrorCodes.VENTE_NOT_FOUND));

    }

    @Override
    public VentesDto findByCode(String code) {
        
        if (StringUtils.hasLength(code)){
            log.error("Ventes code is null");
            return null;
        }

        return ventesRepository.findVentesByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new InvalidEntityException("Aucune vente avec le code " + code + " n'a été trouvée dans la BDD",
                        ErrorCodes.VENTE_NOT_FOUND));

    }

    @Override
    public List<VentesDto> findAll() {

        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .toList();

    }

    @Override
    public void delete(Integer id) {

        if (id == null){
            log.error("Ventes ID is null");
            return;
        }

        ventesRepository.deleteById(id);

    }
}
