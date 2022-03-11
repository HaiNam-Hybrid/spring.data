package com.example.spring.data.service.serviceImpl;

import com.example.spring.data.model.Category;
import com.example.spring.data.repository.CategoryRepo;
import com.example.spring.data.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    @Override
    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }
}
