package com.hamidou.gestiondestock.controller;

import com.hamidou.gestiondestock.controller.api.CategoryApi;
import com.hamidou.gestiondestock.dto.CategoryDto;
import com.hamidou.gestiondestock.services.CategoryService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController implements CategoryApi {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @Override
    public CategoryDto findById(Integer id) {
        return categoryService.findById(id);
    }

    @Override
    public CategoryDto findByCodeCategory(String code) {
        return categoryService.findByCodeCategory(code);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        categoryService.deleteById(id);
    }
}
