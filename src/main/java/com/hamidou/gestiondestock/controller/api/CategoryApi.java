package com.hamidou.gestiondestock.controller.api;

import com.hamidou.gestiondestock.dto.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hamidou.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = "Categories", description = "Gestion des catégories")
public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Enregistrer une catégorie",
            description = "Cette méthode permet d'enregistrer ou de modifier une catégorie",
            responses = {
                    @ApiResponse(responseCode = "200", description = "L'objet catégorie créée / modifiée"),
                    @ApiResponse(responseCode = "400", description = "L'objet catégorie n'est pas valide")
            }
    )
    public CategoryDto save(@RequestBody CategoryDto categoryDto);

    @GetMapping(value = APP_ROOT + "/categorie/{idCategorie}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Rechercher une categorie par ID",
            description = "Cette méthode permet de chercher une categorie par son ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "La categorie a été trouvée dans la BD"),
                    @ApiResponse(responseCode = "404", description = "Aucune categorie n'existe dans la BD avec l'ID fourni")
            }
    )
    public CategoryDto findById(@PathVariable(value = "idCategory") Integer id);

    @GetMapping(value = APP_ROOT + "/categorie/code/{codeCategorie}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Rechercher une categorie par code",
            description = "Cette méthode permet de chercher une categorie par son code",
            responses = {
                    @ApiResponse(responseCode = "200", description = "La categorie a été trouvée dans la BD"),
                    @ApiResponse(responseCode = "404", description = "Aucune categorie n'existe dans la BD avec le code fourni")
            }
    )
    public CategoryDto findByCodeCategory(@PathVariable(value = "codeCategory") String code);

    @GetMapping(value = APP_ROOT + "/categorie/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Liste des catégories",
            description = "Cette méthode permet de chercher et renvoyer la liste des catégories existantes dans la BD",
            responses = {
                    @ApiResponse(responseCode = "200", description = "La liste des catégories / Une liste vide")
            }
    )
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categorie/delete/{idCategory}")
    @Operation(
            summary = "Supprimer une categorie",
            description = "Cette méthode permet de supprimer une categorie par son ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "La categorie a été supprimé")
            }
    )
    void deleteById(@PathVariable(value = "idCategory") Integer id);

}
