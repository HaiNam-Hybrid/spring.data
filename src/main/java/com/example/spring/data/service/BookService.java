package com.example.spring.data.service;

import com.example.spring.data.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findByNameBook(String name);
    Optional<Book> findById(Long id);
    List<Book> findAllBooks();
    List<Book> createBook(List<Book> books);
    Book updateBook(Book book);
}
