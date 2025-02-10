package com.hamidou.gestiondestock.controller.api;

import com.hamidou.gestiondestock.dto.ArticleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.hamidou.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = "Articles", description = "Gestion des articles")
public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Enregistrer un article",
            description = "Cette méthode permet d'enregistrer ou de modifier un article",
            responses = {
                    @ApiResponse(responseCode = "200", description = "L'objet article créé / modifié"),
                    @ApiResponse(responseCode = "400", description = "L'objet article n'est pas valide")
            }
    )
    ArticleDto save(@RequestBody ArticleDto dto);

    @GetMapping(value = APP_ROOT + "/article/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Rechercher un article par ID",
            description = "Cette méthode permet de chercher un article par son ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "L'article a été trouvé dans la BD"),
                    @ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la BD avec l'ID fourni")
            }
    )
    ArticleDto findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value = APP_ROOT + "/article/code/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Rechercher un article par code",
            description = "Cette méthode permet de chercher un article par son code",
            responses = {
                    @ApiResponse(responseCode = "200", description = "L'article a été trouvé dans la BD"),
                    @ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la BD avec le code fourni")
            }
    )
    ArticleDto findByCodeArticle(@PathVariable String codeArticle);

    @GetMapping(value = APP_ROOT + "/article/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Liste des articles",
            description = "Cette méthode permet de chercher et renvoyer la liste des articles existants dans la BD",
            responses = {
                    @ApiResponse(responseCode = "200", description = "La liste des articles / Une liste vide")
            }
    )
    List<ArticleDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/article/delete/{idArticle}")
    @Operation(
            summary = "Supprimer un article",
            description = "Cette méthode permet de supprimer un article par son ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "L'article a été supprimé")
            }
    )
    void delete(@PathVariable("idArticle") Integer id);
}
