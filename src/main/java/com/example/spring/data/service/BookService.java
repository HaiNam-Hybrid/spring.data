package com.example.spring.data.service;

import com.example.spring.data.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findByNameBook(String name);
    List<Book> findAllBooks();
}
