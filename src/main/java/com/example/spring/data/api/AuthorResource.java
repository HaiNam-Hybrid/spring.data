package com.example.spring.data.api;

import com.example.spring.data.model.Author;
import com.example.spring.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/author/create")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<List<Author>> createAuthor(@RequestBody List<Author> authors) {
        List<Author> result = authorService.createAuthor(authors);
            return ResponseEntity.ok().body(result);
    }

    @PutMapping("/author/update")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
        Author result = authorService.updateAuthor(author);
        return ResponseEntity.ok().body(result);
    }
}
