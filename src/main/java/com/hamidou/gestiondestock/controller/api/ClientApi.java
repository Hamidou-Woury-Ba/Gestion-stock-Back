package com.hamidou.gestiondestock.controller.api;

import com.hamidou.gestiondestock.dto.ClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hamidou.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = "Clients", description = "Gestion des clients")
public interface ClientApi {

    @PostMapping(value = APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Enregistrer un client",
            description = "Cette méthode permet d'enregistrer ou de modifier un client",
            responses = {
                    @ApiResponse(responseCode = "200", description = "L'objet client créé / modifié"),
                    @ApiResponse(responseCode = "400", description = "L'objet client n'est pas valide")
            }
    )
    ClientDto save(@RequestBody ClientDto clientDto);

    @GetMapping(value = APP_ROOT + "/client/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Rechercher un client par ID",
            description = "Cette méthode permet de chercher un client par son ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Le client a été trouvé dans la BD"),
                    @ApiResponse(responseCode = "404", description = "Aucun client n'existe dans la BD avec l'ID fourni")
            }
    )
    ClientDto findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = APP_ROOT + "/client/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Liste des client",
            description = "Cette méthode permet de chercher et renvoyer la liste des clients existants dans la BD",
            responses = {
                    @ApiResponse(responseCode = "200", description = "La liste des clients / Une liste vide")
            }
    )
    List<ClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/client/delete/{idClient}")
    @Operation(
            summary = "Supprimer un client",
            description = "Cette méthode permet de supprimer un client par son ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Le client a été supprimé")
            }
    )
    void delete(@PathVariable("idClient") Integer id);

}
