package com.example.spring.data.service;

import com.example.spring.data.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

   Optional<Author>  findById(Long id);
    List<Author> findByNameLike(String name);
    List<Author> findAll();

    List<Author> saveAuthor(List<Author> authors);
}
