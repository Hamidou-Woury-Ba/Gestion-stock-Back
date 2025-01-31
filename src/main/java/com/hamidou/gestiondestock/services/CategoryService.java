package com.hamidou.gestiondestock.services;

import com.hamidou.gestiondestock.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    public CategoryDto save(CategoryDto categoryDto);

    public CategoryDto findById(Integer id);

    public CategoryDto findByCodeCategory(String codeCategory);

    List<CategoryDto> findAll();

    void deleteById(Integer id);

}
