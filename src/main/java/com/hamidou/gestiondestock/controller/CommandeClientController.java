package com.hamidou.gestiondestock.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.hamidou.gestiondestock.controller.api.CommandeClientApi;
import com.hamidou.gestiondestock.dto.CommandeClientDto;
import com.hamidou.gestiondestock.services.CommandeClientService;

@RestController
public class CommandeClientController implements CommandeClientApi {
    
    private CommandeClientService commandeClientService;

    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto dto) {
        return ResponseEntity.ok(commandeClientService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Integer idCommandeClient) {
        return ResponseEntity.ok(commandeClientService.findById(idCommandeClient));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String codeCommandeClient) {
        return ResponseEntity.ok(commandeClientService.findByCode(codeCommandeClient));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(commandeClientService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Integer idCommandeClient) {
        commandeClientService.delete(idCommandeClient);
        return ResponseEntity.ok().build();
    }

}
