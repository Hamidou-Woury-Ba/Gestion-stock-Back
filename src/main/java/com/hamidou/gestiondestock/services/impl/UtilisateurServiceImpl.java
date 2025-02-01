package com.hamidou.gestiondestock.services.impl;

import com.hamidou.gestiondestock.dto.UtilisateurDto;
import com.hamidou.gestiondestock.exception.EntityNotFoundException;
import com.hamidou.gestiondestock.exception.ErrorCodes;
import com.hamidou.gestiondestock.exception.InvalidEntityException;
import com.hamidou.gestiondestock.model.Utilisateur;
import com.hamidou.gestiondestock.repository.UtilisateurRepository;
import com.hamidou.gestiondestock.services.UtilisateurService;
import com.hamidou.gestiondestock.validator.UtilisateurValidator;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        
        List<String> errors = UtilisateurValidator.validate(utilisateurDto);

        if (!errors.isEmpty()) {
            log.error("Utilisateur is not valid {}", utilisateurDto);
            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_FOUND, errors);
        }

        Utilisateur savedUtilisateur = utilisateurRepository.save(UtilisateurDto.toEntity(utilisateurDto));

        return UtilisateurDto.fromEntity(savedUtilisateur);

    }

    @Override
    public UtilisateurDto findById(Integer id) {
        
        if (id == null) {
            log.error("Utilisateur ID is null");
            return null;
        }

        return utilisateurRepository.findById(id)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'ID = " + id + " n'a été trouvé dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();

        return utilisateurs.stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("Utilisateur ID is null");
            return;
        }

        utilisateurRepository.deleteById(id);

    }
}
