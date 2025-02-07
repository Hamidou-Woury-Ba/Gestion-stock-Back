package com.hamidou.gestiondestock.services;

import com.hamidou.gestiondestock.dto.MouvementStockDto;

import java.math.BigDecimal;
import java.util.List;

public interface MouvementStockService {

    BigDecimal stockReelArticle(Integer idArticle);

    List<MouvementStockDto> mouvementStockArticle(Integer idArticle);

    MouvementStockDto entreeStock(MouvementStockDto mouvementStockDto);

    MouvementStockDto sortieStock(MouvementStockDto mouvementStockDto);

    MouvementStockDto correctionStockPos(MouvementStockDto mouvementStockDto);

    MouvementStockDto correctionStockNeg(MouvementStockDto mouvementStockDto);

}
