package com.hamidou.gestiondestock.services.impl;

import com.hamidou.gestiondestock.dto.VentesDto;
import com.hamidou.gestiondestock.services.VentesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {
    @Override
    public VentesDto save(VentesDto dto) {
        return null;
    }

    @Override
    public VentesDto findById(Integer id) {
        return null;
    }

    @Override
    public VentesDto findByCode(String code) {
        return null;
    }

    @Override
    public List<VentesDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
