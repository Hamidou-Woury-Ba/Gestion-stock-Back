package com.hamidou.gestiondestock.dto;

import com.hamidou.gestiondestock.model.Article;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CategoryDto {

    private String code;

    private String designation;

    private List<ArticleDto> articles;

}
