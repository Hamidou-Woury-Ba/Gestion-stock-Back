package com.hamidou.gestiondestock.controller.api;

import com.hamidou.gestiondestock.dto.ArticleDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.hamidou.gestiondestock.utils.Constants.APP_ROOT;

public interface ArticleApi {

    @PostMapping(value =APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto save(@RequestBody ArticleDto dto);

    @GetMapping(value = APP_ROOT + "/article/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value = APP_ROOT + "/article/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto findByCodeArticle(@PathVariable String codeArticle);

    @GetMapping(value = APP_ROOT + "/article/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> findAll();

    @PutMapping(value = APP_ROOT + "/article/delete/{idArticle}")
    void delete(@PathVariable("idArticle") Integer id);

}
