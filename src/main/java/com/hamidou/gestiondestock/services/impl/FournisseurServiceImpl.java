package com.hamidou.gestiondestock.services.impl;

import com.hamidou.gestiondestock.dto.FournisseurDto;
import com.hamidou.gestiondestock.exception.EntityNotFoundException;
import com.hamidou.gestiondestock.exception.ErrorCodes;
import com.hamidou.gestiondestock.exception.InvalidEntityException;
import com.hamidou.gestiondestock.model.Fournisseur;
import com.hamidou.gestiondestock.repository.FournisseurRepository;
import com.hamidou.gestiondestock.services.FournisseurService;
import com.hamidou.gestiondestock.validator.FournisseurValidator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;

    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {

        List<String> errors = FournisseurValidator.validate(fournisseurDto);

        if (!errors.isEmpty()) {
            log.error("Fournisseur is not valid {}", fournisseurDto);
            throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID,
                    errors);
        }

        Fournisseur savedFournisseur = fournisseurRepository.save(FournisseurDto.toEntity(fournisseurDto));

        return FournisseurDto.fromEntity(savedFournisseur);

    }

    @Override
    public FournisseurDto findById(Integer id) {

        if (id == null) {
            log.error("Fournisseur ID is null");
            return null;
        }

        return fournisseurRepository.findById(id)
                .map(FournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun fournisseur avec l'ID = " + id + " n'a été trouvé dans la BDD",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND));

    }

    @Override
    public List<FournisseurDto> findAll() {

        List<Fournisseur> fournisseurs = fournisseurRepository.findAll();

        return fournisseurs.stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("Fournisseur ID is null");
            return;
        }

        fournisseurRepository.deleteById(id);

    }
}
