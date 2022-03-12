package com.example.spring.data.repository;

import com.example.spring.data.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {

    //Author findById(Long id);
    List<Author> findByAuthorNameLike(String name);
}
