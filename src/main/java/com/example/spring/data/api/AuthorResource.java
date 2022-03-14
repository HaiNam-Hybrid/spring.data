package com.example.spring.data.api;

import com.example.spring.data.model.Author;
import com.example.spring.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorResource {
    @Autowired
    AuthorService authorService;

    @GetMapping("/author/all")
    public ResponseEntity<List<Author>> getAllAuthor() {
        return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK) ;
    }

    @GetMapping("/author/{name}")
    public ResponseEntity<List<Author>> searchAuthorNameLike(@PathVariable(name = "name") String name) {
        return new ResponseEntity<>(authorService.findByNameLike(name), HttpStatus.OK) ;
    }

    @PutMapping("/author/update")
    public ResponseEntity<List<Author>> updateAuthor(@RequestBody List<Author> authors) {
        List<Author> result = authorService.saveAuthor(authors);
            return ResponseEntity.ok().body(result);
    }
}
