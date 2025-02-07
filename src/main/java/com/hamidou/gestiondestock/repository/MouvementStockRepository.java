package com.hamidou.gestiondestock.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hamidou.gestiondestock.model.MouvementStock;

public interface MouvementStockRepository extends JpaRepository<MouvementStock, Integer> {

    @Query("select sum(m.quantite) from MouvementStock m where m.article.id = :idArticle")
    BigDecimal stockReelArticle(@Param("idArticle") Integer idArticle);

    List<MouvementStock> findAllByArticleId(Integer idArticle);

}
