package com.example.spring.data.repository;

import com.example.spring.data.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    List<Category> findByCategoryNameLike(String name);
    Category findByCategoryName(String name);
}
