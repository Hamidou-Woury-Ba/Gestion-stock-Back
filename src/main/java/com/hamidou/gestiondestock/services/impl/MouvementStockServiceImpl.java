package com.hamidou.gestiondestock.services.impl;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hamidou.gestiondestock.dto.MouvementStockDto;
import com.hamidou.gestiondestock.exception.ErrorCodes;
import com.hamidou.gestiondestock.exception.InvalidEntityException;
import com.hamidou.gestiondestock.model.TypeMouvementStock;
import com.hamidou.gestiondestock.repository.MouvementStockRepository;
import com.hamidou.gestiondestock.services.ArticleService;
import com.hamidou.gestiondestock.services.MouvementStockService;
import com.hamidou.gestiondestock.validator.MouvementStockValidator;

@Service
@Slf4j
public class MouvementStockServiceImpl implements MouvementStockService {

  private MouvementStockRepository repository;
  private ArticleService articleService;

  @Autowired
  public MouvementStockServiceImpl(MouvementStockRepository repository, ArticleService articleService) {
    this.repository = repository;
    this.articleService = articleService;
  }

  @Override
  public BigDecimal stockReelArticle(Integer idArticle) {

    if (idArticle == null) {
      log.warn("ID article is NULL");
      return BigDecimal.valueOf(-1);
    }
    articleService.findById(idArticle);

    return repository.stockReelArticle(idArticle);

  }

  @Override
  public List<MouvementStockDto> mouvementStockArticle(Integer idArticle) {

    return repository.findAllByArticleId(idArticle).stream()
        .map(MouvementStockDto::fromEntity)
        .collect(Collectors.toList());

  }

  @Override
  public MouvementStockDto entreeStock(MouvementStockDto dto) {

    return entreePositive(dto, TypeMouvementStock.ENTREE);

  }

  @Override
  public MouvementStockDto sortieStock(MouvementStockDto dto) {

    return sortieNegative(dto, TypeMouvementStock.SORTIE);

  }

  @Override
  public MouvementStockDto correctionStockPos(MouvementStockDto dto) {

    return entreePositive(dto, TypeMouvementStock.CORRECTION_POS);
    
  }

  @Override
  public MouvementStockDto correctionStockNeg(MouvementStockDto dto) {
    return sortieNegative(dto, TypeMouvementStock.CORRECTION_NEG);
  }

  private MouvementStockDto entreePositive(MouvementStockDto dto, TypeMouvementStock TypeMouvementStock) {
    List<String> errors = MouvementStockValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Article is not valid {}", dto);
      throw new InvalidEntityException("Le mouvement du stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
    }
    dto.setQuantite(
        BigDecimal.valueOf(
            Math.abs(dto.getQuantite().doubleValue())
        )
    );
    dto.setTypeMouvementStock(TypeMouvementStock);
    return MouvementStockDto.fromEntity(
        repository.save(MouvementStockDto.toEntity(dto))
    );
  }

  private MouvementStockDto sortieNegative(MouvementStockDto dto, TypeMouvementStock TypeMouvementStock) {
    List<String> errors = MouvementStockValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Article is not valid {}", dto);
      throw new InvalidEntityException("Le mouvement du stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
    }
    dto.setQuantite(
        BigDecimal.valueOf(
            Math.abs(dto.getQuantite().doubleValue()) * -1
        )
    );
    dto.setTypeMouvementStock(TypeMouvementStock);
    return MouvementStockDto.fromEntity(
        repository.save(MouvementStockDto.toEntity(dto))
    );
  }
}