package com.example.spring.data.service.serviceImpl;

import com.example.spring.data.model.Book;
import com.example.spring.data.repository.BookRepo;
import com.example.spring.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepo bookRepo;

    @Override
    public List<Book> findByNameBook(String name) {
        return bookRepo.findBookByBookNameLike("%"+name+"%");
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

}
