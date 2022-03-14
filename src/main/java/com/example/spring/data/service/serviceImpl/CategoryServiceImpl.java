package com.example.spring.data.service.serviceImpl;

import com.example.spring.data.model.Category;
import com.example.spring.data.repository.CategoryRepo;
import com.example.spring.data.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    @Override
    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public List<Category> findByNameCategory(String name) {
        return categoryRepo.findByCategoryNameLike("%"+name+"%");
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepo.findById(id);
    }

    @Override
    public List<Category> createCategory(List<Category> categories) {
        return categoryRepo.saveAll(categories);
    }

    @Override
    public Category findByCategoryName(String name) {
        return categoryRepo.findByCategoryName(name);
    }
}
