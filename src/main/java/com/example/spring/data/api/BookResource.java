package com.example.spring.data.api;

import com.example.spring.data.model.Author;
import com.example.spring.data.model.Book;
import com.example.spring.data.model.Category;
import com.example.spring.data.service.AuthorService;
import com.example.spring.data.service.BookService;
import com.example.spring.data.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")

public class BookResource {
    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    AuthorService authorService;
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

    @PostMapping("/book/create")
    public ResponseEntity<List<Book>> updateAuthor(@RequestBody List<Book> books) {
        List<Book> result = bookService.createBook(books);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/book/update")
    @Transactional
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        //check author exist
        Author author = authorService.findByName(book.getAuthor().getAuthorName());
        Set<Category> categories = book.getCategories();
        Set<Category> categorySet = new HashSet<>();
        List<Category> categoryList = new ArrayList<>();
        //check category exist, create new category if category's name is new
        categories.stream().forEach(item ->{
            Category category = categoryService.findByCategoryName(item.getCategoryName());
            if (category != null) {
                categorySet.add(category);
            } else {
                categoryList.add(item);
                categoryService.createCategory(categoryList);
                categorySet.add(item);
            }
            book.setCategories(categorySet);
        });
// check author to create if new author name
        if (author != null) {
            book.setAuthor(author);
        } else {
            authorService.updateAuthor(book.getAuthor());
        }
        Book result = bookService.updateBook(book);
        return ResponseEntity.ok().body(result);
    }
}
