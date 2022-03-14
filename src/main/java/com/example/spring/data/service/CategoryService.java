package com.example.spring.data.service;

import com.example.spring.data.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAllCategories();
    List<Category> findByNameCategory(String name);
    Optional<Category> findById(Long id);
    List<Category> createCategory(List<Category> categories);
    Category findByCategoryName(String name);
}
