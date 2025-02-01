package com.hamidou.gestiondestock.services.impl;

import com.hamidou.gestiondestock.dto.ClientDto;
import com.hamidou.gestiondestock.exception.EntityNotFoundException;
import com.hamidou.gestiondestock.exception.ErrorCodes;
import com.hamidou.gestiondestock.exception.InvalidEntityException;
import com.hamidou.gestiondestock.model.Client;
import com.hamidou.gestiondestock.repository.ClientRepository;
import com.hamidou.gestiondestock.services.ClientService;
import com.hamidou.gestiondestock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceApi implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceApi(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {

        List<String> errors = ClientValidator.validate(clientDto);

        if (!errors.isEmpty()){
            log.error("Client n'est pas valide {}", clientDto);
            throw new InvalidEntityException("Le client n'est valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }

        Client savedClient = clientRepository.save(ClientDto.toEntity(clientDto));

        return ClientDto.fromEntity(savedClient);

    }

    @Override
    public ClientDto findById(Integer id) {

        if (id == null){
            log.error("Client ID is null");
            return null;
        }

        return clientRepository.findById(id)
                .map(ClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun client avec l'ID = " + id + " n'a été trouvé dans la BDD",
                        ErrorCodes.CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<ClientDto> findAll() {

        List<Client> client = clientRepository.findAll();

        return client.stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null){
            log.error("ID is null");
            return;
        }

        clientRepository.deleteById(id);    }
}
