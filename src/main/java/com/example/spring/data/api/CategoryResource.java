package com.example.spring.data.api;

import com.example.spring.data.model.Category;
import com.example.spring.data.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryResource {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/category/getAll")
    public ResponseEntity<List<Category>> getAllBooks() {
        return new ResponseEntity<>(categoryService.findAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Optional<Category>> getBook(@PathVariable(name = "id") Long id) {
        Optional<Category> result = categoryService.findById(id);
        if (result == null) {
            throw new NullPointerException("No data found with book id");
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PostMapping("/category/update")
    public ResponseEntity<List<Category>> updateAuthor(@RequestBody List<Category> categories) {
        List<Category> result = categoryService.createCategory(categories);
            return ResponseEntity.ok().body(result);
    }
}
