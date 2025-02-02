package com.hamidou.gestiondestock.services.impl;

import com.hamidou.gestiondestock.dto.MouvementStockDto;
import com.hamidou.gestiondestock.services.MouvementStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class MouvementStockServiceImpl implements MouvementStockService {
    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        return null;
    }

    @Override
    public List<MouvementStockDto> mvtStkArticle(Integer idArticle) {
        return null;
    }

    @Override
    public MouvementStockDto entreeStock(MouvementStockDto dto) {
        return null;
    }

    @Override
    public MouvementStockDto sortieStock(MouvementStockDto dto) {
        return null;
    }

    @Override
    public MouvementStockDto correctionStockPos(MouvementStockDto dto) {
        return null;
    }

    @Override
    public MouvementStockDto correctionStockNeg(MouvementStockDto dto) {
        return null;
    }
}
