package com.example.spring.data.service.serviceImpl;

import com.example.spring.data.model.Author;
import com.example.spring.data.repository.AuthorRepo;
import com.example.spring.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepo authorRepo;
    @Override
    public Optional<Author> findById(Long id) {
        return authorRepo.findById(id);
    }

    @Override
    public List<Author> findByNameLike(String name) {
        return authorRepo.findByAuthorNameLike("%"+name+"%");
    }

    @Override
    public List<Author> findAll() {
        return authorRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Author> createAuthor(List<Author> authors){
        return authorRepo.saveAll(authors);
    }

    @Override
    public Author updateAuthor(Author author) {
//        Author oldAuthor = authorRepo.findById(author.getId()).orElseThrow(()->new NullPointerException("No Author in database"));
        Optional<Author> oldAuthor = authorRepo.findById(author.getId());
        if (oldAuthor.isPresent()) {
            return authorRepo.save(author);
        } else {
            throw new NullPointerException("Author id wrong!");
        }
    }

    @Override
    public Author findByName(String name) {
        return authorRepo.findByAuthorName(name);
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepo.save(author);
    }
}
