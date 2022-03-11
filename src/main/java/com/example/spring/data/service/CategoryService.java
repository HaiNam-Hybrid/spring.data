package com.example.spring.data.service;

import com.example.spring.data.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAllCategories();
}
