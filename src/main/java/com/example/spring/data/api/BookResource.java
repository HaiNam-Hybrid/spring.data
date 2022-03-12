package com.example.spring.data.api;

import com.example.spring.data.model.Author;
import com.example.spring.data.model.Book;
import com.example.spring.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")

public class BookResource {
    @Autowired
    BookService bookService;
    @GetMapping("/book/getAll")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Optional<Book>> getBook(@PathVariable(name = "id") Long id) {
        Optional<Book> result = bookService.findById(id);
        if (result == null) {
            throw new NullPointerException("No data found with book id");
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PutMapping("/book/update")
    public ResponseEntity<Book> updateAuthor(@RequestBody Book book) {
            Book result = bookService.updateBook(book);
            return ResponseEntity.ok().body(result);
    }
}
