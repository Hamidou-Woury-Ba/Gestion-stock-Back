package com.hamidou.gestiondestock.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hamidou.gestiondestock.dto.ArticleDto;
import com.hamidou.gestiondestock.exception.ErrorCodes;
import com.hamidou.gestiondestock.exception.InvalidEntityException;
import com.hamidou.gestiondestock.model.Article;
import com.hamidou.gestiondestock.repository.ArticleRepository;
import com.hamidou.gestiondestock.services.ArticleService;
import com.hamidou.gestiondestock.validator.ArticleValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {

        List<String> errors = ArticleValidator.validate(articleDto);

        if (!errors.isEmpty()) {
            log.error("Article n'est pas valide {}", articleDto);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }

        Article savedArticle = articleRepository.save(ArticleDto.toEntity(articleDto));

        return ArticleDto.fromEntity(savedArticle);
    }

    @Override
    public ArticleDto findById(Integer id) {

        if (id == null) {
            log.error("Article ID is null");
            return null;
        }

        Optional<Article> article = articleRepository.findById(id);

        ArticleDto articleDto = ArticleDto.fromEntity(article.get());

        return Optional.of(articleDto)
                .orElseThrow(() -> new InvalidEntityException(
                        "Aucun article avec l'ID = " + id + " n'a été trouvé dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        
        if (!StringUtils.hasLength(codeArticle)) {
            log.error("Article CODE is null");
            return null;
        }

        Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle);

        ArticleDto articleDto = ArticleDto.fromEntity(article.get());

        return Optional.of(articleDto)
                .orElseThrow(() -> new InvalidEntityException(
                        "Aucun article avec le CODE = " + codeArticle + " n'a été trouvé dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public List<ArticleDto> findAll() {
        
        List<Article> articles = articleRepository.findAll();

        return articles.stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("Article ID is null");
            return;
        }

        articleRepository.deleteById(id);

    }

}
