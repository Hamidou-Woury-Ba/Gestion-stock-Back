package com.hamidou.gestiondestock.services.impl;

import com.hamidou.gestiondestock.dto.CommandeClientDto;
import com.hamidou.gestiondestock.dto.LigneCommandeClientDto;
import com.hamidou.gestiondestock.exception.EntityNotFoundException;
import com.hamidou.gestiondestock.exception.ErrorCodes;
import com.hamidou.gestiondestock.exception.InvalidEntityException;
import com.hamidou.gestiondestock.model.Article;
import com.hamidou.gestiondestock.model.Client;
import com.hamidou.gestiondestock.model.CommandeClient;
import com.hamidou.gestiondestock.model.LigneCommandeClient;
import com.hamidou.gestiondestock.repository.ArticleRepository;
import com.hamidou.gestiondestock.repository.ClientRepository;
import com.hamidou.gestiondestock.repository.CommandeClientRepository;
import com.hamidou.gestiondestock.repository.LigneCommandeClientRepository;
import com.hamidou.gestiondestock.services.CommandeClientService;
import com.hamidou.gestiondestock.validator.CommandeClientValidator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private ArticleRepository articleRepository;
    private ClientRepository clientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;

    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, ArticleRepository articleRepository, ClientRepository clientRepository, LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.articleRepository = articleRepository;
        this.clientRepository = clientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }


    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {

        List<String> errors = CommandeClientValidator.validate(commandeClientDto);

        if (!errors.isEmpty()) {
            log.error("Commande client is not valid {}", commandeClientDto);
            throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        Optional<Client> client = clientRepository.findById(commandeClientDto.getClient().getId());

        if (!client.isEmpty()){
            log.warn("Client with ID {} was not found in the DB", commandeClientDto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID " + commandeClientDto.getClient().getId() + " n'a été trouvé dans la BDD", ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (commandeClientDto.getLigneCommandeClient() != null){
            commandeClientDto.getLigneCommandeClient().forEach(LigCmdCt -> {
                if (LigCmdCt.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(LigCmdCt.getArticle().getId());
                    if (article.isEmpty()){
                        articleErrors.add("L'article avec l'ID " + LigCmdCt.getArticle().getId() + " n'existe pas");
                    }
                }else{
                    errors.add("Impossible d'enregistrer une commande avec un article NULL");
                }
            });
        }

        if (articleErrors.isEmpty()) {
            log.warn("Un ou plusieurs articles de la commande n'ont pas été trouvés dans la BDD");
            throw new InvalidEntityException("Un ou plusieurs articles de la commande n'ont pas été trouvés dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeClient savedCommandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));

        if(commandeClientDto.getLigneCommandeClient() != null){
            commandeClientDto.getLigneCommandeClient().forEach(ligCmdClt -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
                ligneCommandeClient.setCommandeClient(savedCommandeClient);
                ligneCommandeClientRepository.save(LigneCommandeClientDto.toEntity(ligCmdClt));
            });
        }

        return CommandeClientDto.fromEntity(savedCommandeClient);

    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (id == null){
            log.error("Commande client ID is NULL");
            return null;
            
        }
        
        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune commande client avec l'ID " + id + " n'a été trouvée dans la BDD", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        
        if (StringUtils.hasLength(code)){
            log.error("Commande client code is NULL");
            return null;
        }

        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune commande client avec le code " + code + " n'a été trouvée dans la BDD", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND)); 

    }

    @Override
    public List<CommandeClientDto> findAll() {
        
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public void delete(Integer id) {
        
        if (id == null){
            log.error("Commande client ID is NULL");
            return;
        }

        commandeClientRepository.deleteById(id);
    }
}
