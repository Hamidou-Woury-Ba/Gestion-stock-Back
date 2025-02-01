package com.hamidou.gestiondestock.services.impl;

import com.hamidou.gestiondestock.dto.EntrepriseDto;
import com.hamidou.gestiondestock.exception.ErrorCodes;
import com.hamidou.gestiondestock.exception.InvalidEntityException;
import com.hamidou.gestiondestock.exception.EntityNotFoundException;
import com.hamidou.gestiondestock.model.Entreprise;
import com.hamidou.gestiondestock.services.EntrepriseService;
import com.hamidou.gestiondestock.validator.EntrepriseValidator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.hamidou.gestiondestock.repository.EntrepriseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }


    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        
        List<String> errors = EntrepriseValidator.validate(entrepriseDto);

        if (!errors.isEmpty()) {
            log.error("Entreprise is not valid {}", entrepriseDto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }

        EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto)));

        return savedEntreprise;

    }

    @Override
    public EntrepriseDto findById(Integer id) {
        
        if (id == null) {
            log.error("Entreprise ID is null");
            return null;
        }

        return entrepriseRepository.findById(id)
                .map(EntrepriseDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune entreprise avec l'ID = " + id + " n'a été trouvée dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND));

    }

    @Override
    public List<EntrepriseDto> findAll() {
       
        List<Entreprise> entreprises = entrepriseRepository.findAll();

        return entreprises.stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("Entreprise ID is null");
            return;
        }

        entrepriseRepository.deleteById(id);

    }
}
