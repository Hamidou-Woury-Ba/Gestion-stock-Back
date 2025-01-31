package com.hamidou.gestiondestock.services.impl;

import com.hamidou.gestiondestock.dto.CategoryDto;
import com.hamidou.gestiondestock.exception.EntityNotFoundException;
import com.hamidou.gestiondestock.exception.ErrorCodes;
import com.hamidou.gestiondestock.exception.InvalidEntityException;
import com.hamidou.gestiondestock.model.Category;
import com.hamidou.gestiondestock.repository.CategoryRepository;
import com.hamidou.gestiondestock.services.CategoryService;
import com.hamidou.gestiondestock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public CategoryDto save(CategoryDto categoryDto) {

        List<String> errors = CategoryValidator.validate(categoryDto);

        if(!errors.isEmpty()){
          log.error("Article n'est pas valide {}", categoryDto);
          throw(new InvalidEntityException("Cette catégorie n'est pas valide", ErrorCodes.CATEGORY_NOT_FOUND, errors));
        }

        Category savedCategory = categoryRepository.save(CategoryDto.toEntity(categoryDto));

        return CategoryDto.fromEntity(savedCategory);
    }

    @Override
    public CategoryDto findById(Integer id) {

        if (id == null){
          log.error("Category ID is null");
        }

        Optional<Category> category = categoryRepository.findById(id);

        CategoryDto categoryDto = CategoryDto.fromEntity(category.get());

        return Optional.of(categoryDto)
                .orElseThrow(() -> new InvalidEntityException(
                        "Aucune catégory avec l'ID = " + id + " n'a été trouvé dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND
                ));
    }

    @Override
    public CategoryDto findByCodeCategory(String codeCategory) {

        if (!StringUtils.hasLength(codeCategory)){
            log.error("Catégory CodeCategory is null");
            return null;
        }

        Optional<Category> category = categoryRepository.findCategoryByCode(codeCategory);

        CategoryDto categoryDto = CategoryDto.fromEntity(category.get());

        return Optional.of(categoryDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun article avec le CODE = " + codeCategory + " n'a été trouvé dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND
                ));
    }

    @Override
    public List<CategoryDto> findAll() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null){
            log.error("Category ID is null");
            return;
        }

        categoryRepository.deleteById(id);

    }
}
